package com.aiiju.store.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aiiju.common.pa_utils.platform.FileManagerUtil;
import com.aiiju.common.pa_utils.platform.MerchantManagerUtil;
import com.aiiju.common.pa_utils.platform.ShopManagerUtil;
import com.aiiju.common.pa_utils.platform.util.StaticConfig;
import com.aiiju.common.pa_utils.platform.util.TLinx2Util;
import com.aiiju.common.pa_utils.platform.util.TLinxAESCoder;
import com.aiiju.common.pojo.Result;
import com.aiiju.common.util.FtpUtil;
import com.aiiju.mapper.ImageResourceMapper;
import com.aiiju.mapper.PAImagesMapper;
import com.aiiju.mapper.PAMerchantMapper;
import com.aiiju.mapper.PAShopMapper;
import com.aiiju.pojo.ImageResource;
import com.aiiju.pojo.PAImages;
import com.aiiju.pojo.PAMerchant;
import com.aiiju.pojo.PAShop;
import com.aiiju.store.constant.ImageFieldMap;
import com.aiiju.store.service.MerchantService;
import com.aiiju.store.service.PATagPhoneService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import sun.misc.BASE64Encoder;

/**
 * @ClassName MerchantServiceImpl
 * @Description
 * @author zong
 * @CreateDate 2017年7月19日 上午11:44:01
 */
@Service("merchantService")
public class MerchantServiceImpl implements MerchantService {

	private static Logger logger = Logger.getLogger(MerchantServiceImpl.class);
	
	@Autowired
	private PAMerchantMapper merchantMapper;

	@Autowired
	private PAShopMapper paShopMapper;
	
	@Autowired
	private PATagPhoneService paTipPhoneService;
	
	

	@Value("${BASE_URL}")
	private String IMAGE_BASE_URL;

	@Value("${FILI_UPLOAD_PATH}")
	private String FILI_UPLOAD_PATH;

	@Value("${FTP_IP}")
	private String FTP_SERVER_IP;

	@Value("${FTP_PORT}")
	private Integer FTP_SERVER_PORT;

	@Value("${FTP_USERNAME}")
	private String FTP_SERVER_USERNAME;

	@Value("${FTP_PASSWORD}")
	private String FTP_SERVER_PASSWORD;

	@Autowired
	private ImageResourceMapper imageResourceMapper;

	@Autowired
	private PAImagesMapper paImagesMapper;

	BASE64Encoder encoder = new sun.misc.BASE64Encoder();

	@Override
	public Result add(PAMerchant merchant) {
		
		    if("".equals(merchant.getBossTel())||merchant.getBossTel()==null){
//		    	merchant.setBossTel("18257128735");
		    	return Result.build(500, "法人联系电话不能为空！");
		    }
		
			String traId = new Date().getTime() / 1000 + ""; // 时间
//			merchant.setBlNo(blNo);
			merchant.setTraId(traId);
			List<String> clsidList = new ArrayList<String>();
			clsidList.add(merchant.getClsid().split(",")[0]);
			clsidList.add(merchant.getClsid().split(",")[1]);
			merchant.setClsidList(clsidList);
			merchant.setCreateDate(new Date());
			
			/**
			 * 
			 * 第一步，在数据库中添加商户签约信息，然后发送商户签约请求信息，如果失败，则删除数据库信息或反写信息，返回成功，
			 * 反写mct_no相关信息
			 * 
			 */
			logger.info("第一步，添加商户签约信息");
			merchant.setStep("1");// 第一步添加签约信息
			merchantMapper.add(merchant);

			Integer id = merchant.getId();

			if (id != null && id > 0) {

			} else {
				logger.info("保存商户签约信息失败");
				return Result.build(500, "保存商户签约信息失败");
			}

			JSONObject addMerJson = new JSONObject();
			try {
				 addMerJson = MerchantManagerUtil.add(merchant); // 添加（即签约 商户信息
			} catch (Exception e) {
				
				logger.info("警告：添加商户时，出错，请查看第三方接口调用情况");
				return Result.build(500, "保存商户签约信息失败");
				//throw new RuntimeException(); // 抛出异常回滚
			}
			

			Integer addMerErrcode = (Integer) addMerJson.get("errcode");
			String addMerMsg = (String) addMerJson.get("msg");

			if (0 != addMerErrcode) {
				deleteMerchantFromDB(id); // 添加失败删除本地数据
				return Result.build(500, addMerMsg);
				//throw new RuntimeException(); // 抛出异常回滚
			}

			Object addMerData = addMerJson.get("data");
			Result addMerResultJson = getReturnData(addMerJson, addMerData, merchant, null, addMerErrcode, addMerMsg);

			if (addMerResultJson.getStatus() == 500) {
				return addMerResultJson; // 返回 添加失败的具体信息
			} else {
				String respData = addMerResultJson.getMsg();
				JSONObject returnJson = JSONObject.fromObject(respData);
				String mctNo = (String) returnJson.get("mct_no");
				merchant.setMctNo(mctNo);
				updateFeedbackMsg(merchant);
				/**
				 * 
				 * 第二步 ，提审商户签约信息，返回反写返回信息
				 * 
				 */
				logger.info("第二步 ，提审商户签约信息");
				merchant.setStep("2");
				
				JSONObject sbCheckJson = new JSONObject();
				try {
					 sbCheckJson = MerchantManagerUtil.sbCheck(mctNo);// 提审商户签约信息
				} catch (Exception e) {
					logger.info("警告：提审商户时，出错，请查看第三方接口调用情况,sbCheckJson="+sbCheckJson);
					return Result.build(500, "警告：提审商户时，出错，请查看第三方接口调用情况");
				}
				
				
				Integer sbCheckErrcode = (Integer) sbCheckJson.get("errcode");
				String sbCheckMsg = (String) sbCheckJson.get("msg");

				Object sbCheckData = sbCheckJson.get("data");
				Result sbCheckResultJson = getReturnData(sbCheckJson, sbCheckData, merchant, null, sbCheckErrcode,
						sbCheckMsg);

				if (sbCheckResultJson.getStatus() == 500) {
					updateFeedbackMsg(merchant);
					return sbCheckResultJson; // 返回 提审失败的具体信息
				} else {
					updateFeedbackMsg(merchant);
					/**
					 * 
					 * 第三步 ，审核商户签约信息，返回反写返回信息
					 * 
					 */
					logger.info("第三步 ，审核商户签约信息");
					merchant.setStep("3");
					
					JSONObject checkJson =new JSONObject();
					try {
						 checkJson = MerchantManagerUtil.check(mctNo, "1", "允许通过");// 审核商户签约信息
					} catch (Exception e) {
						logger.info("警告：审核商户时，出错，请查看第三方接口调用情况");
						return Result.build(500, "警告：审核商户时，出错，请查看第三方接口调用情况");
					}
					
					
					Integer checkErrcode = (Integer) checkJson.get("errcode");
					String checkMsg = (String) checkJson.get("msg");

					Object checkData = checkJson.get("data");
					Result checkResultJson = getReturnData(checkJson, checkData, merchant, null, checkErrcode,
							checkMsg);

					if (checkResultJson.getStatus() == 500) {
						updateFeedbackMsg(merchant);
						return checkResultJson; // 返回 审核失败的具体信息
					} else {
						updateFeedbackMsg(merchant);

						/**
						 * 
						 * 第四步 ，添加门店信息，返回反写返回信息
						 * 
						 */
						merchant.setStep("4");
						logger.info("第四步 ，添加门店信息");
						PAShop paShop = getPAShop(merchant);

						paShopMapper.add(paShop);

						Integer shopid = paShop.getId();

						if (shopid != null && shopid > 0) {

						} else {
							logger.info("保存门店信息失败");
							return Result.build(500, "保存门店信息失败");
						}

						JSONObject addShopJson = ShopManagerUtil.add(paShop);

						Integer addShopErrcode = (Integer) addShopJson.get("errcode");
						String addShopMsg = (String) addShopJson.get("msg");

						if (0 != addShopErrcode) {
							paShopMapper.deletePAShopByIdFromDB(shopid);// 添加失败删除本地数据
						}
						Object addShopData = addShopJson.get("data");
						Result addShopResultJson = getReturnData(addShopJson, addShopData, null, paShop, addShopErrcode,
								addShopMsg);

						if (addShopResultJson.getStatus() == 500) {
							return addShopResultJson; // 返回 添加失败的具体信息
						} else {
							String respShopData = addShopResultJson.getMsg();
							JSONObject returnShopJson = JSONObject.fromObject(respShopData);
							String shopNo = (String) returnShopJson.get("shop_no");
							paShop.setShopNo(shopNo);
							paShopMapper.updateFeedbackMsg(paShop);

							/**
							 * 
							 * 第二步 ，提审门店信息，返回反写返回信息
							 * 
							 */
							logger.info("第五步 ，提审门店信息");
							paShop.setStep("2");
							JSONObject sbCheckShopJson = ShopManagerUtil.sbCheck(shopNo);// 提审门店信息
							Integer sbCheckShopErrcode = (Integer) sbCheckShopJson.get("errcode");
							String sbCheckShopMsg = (String) sbCheckShopJson.get("msg");

							Object sbCheckShopData = sbCheckShopJson.get("data");
							Result sbCheckShopResultJson = getReturnData(sbCheckShopJson, sbCheckShopData, null, paShop,
									sbCheckShopErrcode, sbCheckShopMsg);

							if (sbCheckShopResultJson.getStatus() == 500) {
								paShopMapper.updateFeedbackMsg(paShop);
								return sbCheckShopResultJson; // 返回 提审失败的具体信息
							} else {
								paShopMapper.updateFeedbackMsg(paShop);

								/**
								 * 
								 * 第三步 ，审核门店信息，返回反写返回信息
								 * 
								 */
								logger.info("第六步 ，审核门店信息");
								paShop.setStep("3");
								JSONObject checkShopJson = ShopManagerUtil.check(shopNo, "1", "允许通过");// 审核商户签约信息
								Integer checkShopErrcode = (Integer) checkShopJson.get("errcode");
								String checkShopMsg = (String) checkShopJson.get("msg");

								Object checkShopData = checkShopJson.get("data");
								Result checkShopResultJson = getReturnData(checkShopJson, checkShopData, null, paShop,
										checkShopErrcode, checkShopMsg);

								if (checkShopResultJson.getStatus() == 500) {
									paShopMapper.updateFeedbackMsg(paShop);
									return checkShopResultJson; // 返回 审核失败的具体信息
								} else {
									paShopMapper.updateFeedbackMsg(paShop);

									/**
									 * 
									 * 第四步 ，获取门店openid 反写数据库中
									 * 
									 */
									logger.info("第七步 ，获取门店openId及openKey");
									JSONObject openInfoJson = ShopManagerUtil.getOpenIdAndKey(shopNo);
									paShop.setStep("4");
									Integer openInfoErrcode = (Integer) openInfoJson.get("errcode");
									String openInfoMsg = (String) openInfoJson.get("msg");

									Object openInfoData = openInfoJson.get("data");
									Result openInfoResultJson = getReturnData(openInfoJson, openInfoData, null, paShop,
											openInfoErrcode, openInfoMsg);

									if (openInfoResultJson.getStatus() == 500) {
										paShopMapper.updateFeedbackMsg(paShop);
										return openInfoResultJson; // 返回
																	// 审核失败的具体信息
									} else {

										String respOpenInfoData = openInfoResultJson.getMsg();
										JSONObject returnOpenInfoJson = JSONObject.fromObject(respOpenInfoData);
										String openId = (String) returnOpenInfoJson.get("open_id");
										String openKey = (String) returnOpenInfoJson.get("open_key");
										paShop.setOpenId(openId);
										paShop.setOpenKey(openKey);
										paShopMapper.updateFeedbackMsg(paShop);
										logger.info("签约步骤完成");
										
										paTipPhoneService.sendTag();//短信通知管理人员，提示给商户制作合同
										
										return Result.build(200, "签约步骤完成");
									}
								}
							}
						}
					}
				}
			}


	}

	@Override
	public Result update(PAMerchant merchant,String updateType) {

//		String traId = new Date().getTime() / 1000 + ""; // 时间
//		merchant.setTraId(traId);
		List<String> clsidList = new ArrayList<String>();
		clsidList.add(merchant.getClsid().split(",")[0]);
		clsidList.add(merchant.getClsid().split(",")[1]);
		merchant.setClsidList(clsidList);
		merchant.setModifyDate(new Date());
		/**
		 * 
		 * 第一步，在数据库中添加商户签约信息，然后发送商户签约请求信息，如果失败，则删除数据库信息或反写信息，返回成功，
		 * 反写mct_no相关信息
		 * 
		 */
		logger.info("第一步，更新商户签约信息");
		merchant.setStep("1");// 第一步更新签约信息
		merchantMapper.update(merchant);

	

		JSONObject editMerJson = new JSONObject();
		try {
			editMerJson = MerchantManagerUtil.edit(merchant); // 修改（即签约 商户信息
		} catch (Exception e) {
			logger.info("警告：修改商户时，出错，请查看第三方接口调用情况");
			//throw new RuntimeException(); // 抛出异常回滚
			return Result.build(500, "修改商户签约信息失败");
			// TODO: handle exception
		}
		

		Integer editMerErrcode = (Integer) editMerJson.get("errcode");
		String  editMerMsg = (String) editMerJson.get("msg");

		if (0 != editMerErrcode&&6060==editMerErrcode) {
			
			return Result.build(6060, "接口返回信息："+editMerMsg);
			
		}

		Object editMerData = editMerJson.get("data");
		Result editMerResultJson = getReturnData(editMerJson, editMerData, merchant, null, editMerErrcode, editMerMsg);

		if (editMerResultJson.getStatus() == 500) {
			return editMerResultJson; // 返回 添加失败的具体信息
		} else {

			updateFeedbackMsg(merchant);
			/**
			 * 
			 * 第二步 ，提审商户签约信息，返回反写返回信息
			 * 
			 */
			logger.info("第二步 ，提审商户签约信息");
			merchant.setStep("2");
			
			JSONObject sbCheckJson = new JSONObject();
			try {
				 sbCheckJson = MerchantManagerUtil.sbCheck(merchant.getMctNo());// 提审商户签约信息
			} catch (Exception e) {
				logger.info("更新签约信息，提审商户签约信息时出错");
				return Result.build(500, "更新签约信息，提审商户签约信息时出错");
			}
			
			
			Integer sbCheckErrcode = (Integer) sbCheckJson.get("errcode");
			String sbCheckMsg = (String) sbCheckJson.get("msg");

			Object sbCheckData = sbCheckJson.get("data");
			Result sbCheckResultJson = getReturnData(sbCheckJson, sbCheckData, merchant, null, sbCheckErrcode,
					sbCheckMsg);

			if (sbCheckResultJson.getStatus() == 500) {
				updateFeedbackMsg(merchant);
				return sbCheckResultJson; // 返回 提审失败的具体信息
			} else {
				updateFeedbackMsg(merchant);
				/**
				 * 
				 * 第三步 ，审核商户签约信息，返回反写返回信息
				 * 
				 */
				logger.info("第三步 ，审核商户签约信息");
				merchant.setStep("3");
				
				JSONObject checkJson =new JSONObject();
				try {
					 checkJson = MerchantManagerUtil.check(merchant.getMctNo(), "1", "允许通过");// 审核商户签约信息
				} catch (Exception e) {
					logger.info("更新签约信息，审核商户签约信息时出错");
					return Result.build(500, "更新签约信息，审核商户签约信息时出错");
				}
				
				
				Integer checkErrcode = (Integer) checkJson.get("errcode");
				String checkMsg = (String) checkJson.get("msg");

				Object checkData = checkJson.get("data");
				Result checkResultJson = getReturnData(checkJson, checkData, merchant, null, checkErrcode,
						checkMsg);

				if (checkResultJson.getStatus() == 500) {
					updateFeedbackMsg(merchant);
					return checkResultJson; // 返回 审核失败的具体信息
				} else {
					updateFeedbackMsg(merchant);

					/**
					 * 
					 * 第四步 ，修改门店信息，返回反写返回信息
					 * 
					 */
					merchant.setStep("4");
					logger.info("第四步 ，修改门店信息");
					PAShop paShop = getPAShop(merchant);

					String shopNo = paShopMapper.getPAShopByStoreId(paShop.getStoreId());
					paShop.setShopNo(shopNo);
					PAShop paShop_default = paShop;
					paShop.setModifyDate(new Date());
					paShopMapper.update(paShop);

	
					JSONObject updateShopJson = ShopManagerUtil.edit(paShop);

					Integer updateShopErrcode = (Integer) updateShopJson.get("errcode");
					String updateShopMsg = (String) updateShopJson.get("msg");

					if (0 != updateShopErrcode) {
						paShopMapper.update(paShop_default);
						logger.info("更新签约商户门店信息时异常，已回滚");
						return Result.build(500, updateShopMsg);
//						throw new RuntimeException(); // 抛出异常回滚
					}
					Object updateShopData = updateShopJson.get("data");
					Result updateShopResultJson = getReturnData(updateShopJson, updateShopData, null, paShop, updateShopErrcode,
							updateShopMsg);

					if (updateShopResultJson.getStatus() == 500) {
						 // 返回 添加失败的具体信息
						logger.info("更新签约商户门店信息时异常，已回滚"+updateShopResultJson.getMsg());
						paShopMapper.updateFeedbackMsg(paShop);
//						throw new RuntimeException(); // 抛出异常回滚
						return updateShopResultJson;
						
					} else {
		
						paShopMapper.updateFeedbackMsg(paShop);

						/**
						 * 
						 * 第二步 ，提审门店信息，返回反写返回信息
						 * 
						 */
						logger.info("第五步 ，提审门店信息");
						paShop.setStep("2");
						JSONObject sbCheckShopJson = ShopManagerUtil.sbCheck(paShop.getShopNo());// 提审门店信息
						Integer sbCheckShopErrcode = (Integer) sbCheckShopJson.get("errcode");
						String sbCheckShopMsg = (String) sbCheckShopJson.get("msg");

						Object sbCheckShopData = sbCheckShopJson.get("data");
						Result sbCheckShopResultJson = getReturnData(sbCheckShopJson, sbCheckShopData, null, paShop,
								sbCheckShopErrcode, sbCheckShopMsg);

						if (sbCheckShopResultJson.getStatus() == 500) {
							paShopMapper.updateFeedbackMsg(paShop);
							return sbCheckShopResultJson; // 返回 提审失败的具体信息
						} else {
							paShopMapper.updateFeedbackMsg(paShop);

							/**
							 * 
							 * 第三步 ，审核门店信息，返回反写返回信息
							 * 
							 */
							logger.info("第六步 ，审核门店信息");
							paShop.setStep("3");
							JSONObject checkShopJson = ShopManagerUtil.check(paShop.getShopNo(), "1", "允许通过");// 审核商户签约信息
							Integer checkShopErrcode = (Integer) checkShopJson.get("errcode");
							String checkShopMsg = (String) checkShopJson.get("msg");

							Object checkShopData = checkShopJson.get("data");
							Result checkShopResultJson = getReturnData(checkShopJson, checkShopData, null, paShop,
									checkShopErrcode, checkShopMsg);

							if (checkShopResultJson.getStatus() == 500) {
								paShopMapper.updateFeedbackMsg(paShop);
								return checkShopResultJson; // 返回 审核失败的具体信息
							} else {
								
								paShopMapper.updateFeedbackMsg(paShop);
								
								paTipPhoneService.sendTag();//短信通知管理人员，提示给商户制作合同
								
								return Result.build(200, "更新签约信息成功");
								
							}
						}
					}
				}
			}
		}

	}

	@Override
	public Result getMerchantInfo(String storeId) {
		PAMerchant merchant = merchantMapper.get(storeId);
		return Result.success(merchant);
	}

	@Override
	public Result deleteMerchantFromDB(Integer id) {
		merchantMapper.deleteMerchantByIdFromDB(id);
		return Result.success();
	}

	@Override
	public Result updateFeedbackMsg(PAMerchant merchant) {
		merchantMapper.updateFeedbackMsg(merchant);
		return Result.success();
	}

	public Result getReturnData(JSONObject respObject, Object dataStr, PAMerchant merchant, PAShop shop,
			Integer errcode, String msg) {

		if (merchant != null) {
			merchant.setErrcode(errcode);
			merchant.setErrcodeMsg(msg);
		}
		if (shop != null) {
			shop.setErrcode(errcode);
			shop.setErrcodeMsg(msg);
		}

		if (!respObject.isEmpty() && (dataStr != null)) {

			if (TLinx2Util.verifySign(respObject, StaticConfig.publickey)) { // 验签成功

				/**
				 * 5 AES解密，并hex2bin
				 */
				String respData = null;
				try {
					respData = TLinxAESCoder.decrypt(dataStr.toString(), StaticConfig.open_key);
				} catch (Exception e) {
					if (merchant != null) {
						merchant.setStepStatus("0"); // 此步骤失败
					}
					if (shop != null) {
						shop.setStepStatus("0"); // 此步骤失败
					}
					return Result.build(500, e.getMessage());
				}
				if (merchant != null) {
					merchant.setStepStatus("1"); // 此步骤成功
				}
				if (shop != null) {
					shop.setStepStatus("1"); // 此步骤成功
				}
				logger.info("接口调用成功，返回内容:" + respData);
				return Result.build(200, respData);

			} else {
				if (merchant != null) {
					merchant.setStepStatus("0"); // 此步骤失败
				}
				if (shop != null) {
					shop.setStepStatus("0"); // 此步骤失败
				}
				return Result.build(500, "验签失败");
			}
		} else {
			if (merchant != null) {
				merchant.setStepStatus("0"); // 此步骤失败
			}
			if (shop != null) {
				shop.setStepStatus("0"); // 此步骤失败
			}
			logger.info("第三方接口调用出错，没有返回data数据，请检查接口传入值是否正确");
			return Result.build(500, "没有返回data数据  "+msg);
		}

	}

	public static PAShop getPAShop(PAMerchant merchant) {

		PAShop paShop = new PAShop();
		paShop.setMctNo(merchant.getMctNo());
		paShop.setAddress(merchant.getAddress());
		paShop.setCityid(merchant.getCityid());
		paShop.setLat(merchant.getLat());
		paShop.setLng(merchant.getLng());
		paShop.setOpenHours(merchant.getOpenHours());
		paShop.setPic1(merchant.getPic1());
		paShop.setPic2(merchant.getPic2());
		paShop.setPic3(merchant.getPic3());
		paShop.setShopFullName(merchant.getMctName());
		paShop.setShopName(merchant.getMctName());
		// paShop.setShopNo(shopNo);
		paShop.setTel(merchant.getTel());
		paShop.setTraId(merchant.getTraId());

		paShop.setCreateDate(merchant.getCreateDate());
		paShop.setMerId(merchant.getId());
		paShop.setParentStoreId(merchant.getParentStoreId());
		paShop.setStoreId(merchant.getStoreId());

		return paShop;
	}

	@Override
	public Result createMctClass() {

		JSONObject mctClassJson = MerchantManagerUtil.mctClass();
		Integer mctClassErrcode = (Integer) mctClassJson.get("errcode");
		String mctClassMsg = (String) mctClassJson.get("msg");

		if (0 != mctClassErrcode) {
			logger.info("接口获取商户类别 失败:" + mctClassMsg);
		}

		Object dataStr = mctClassJson.get("data");

		if (!mctClassJson.isEmpty() && (dataStr != null)) {
			if (TLinx2Util.verifySign(mctClassJson, StaticConfig.publickey)) { // 验签成功
				/**
				 * 5 AES解密，并hex2bin
				 */
				String respData = null;
				try {
					respData = TLinxAESCoder.decrypt(dataStr.toString(), StaticConfig.open_key);
				} catch (Exception e) {
					logger.info("接口获取商户类别 失败:" + e.getMessage());
					e.printStackTrace();
				}
				logger.info("==================响应data内容:" + respData);
				JSONArray jsonArray = JSONArray.fromObject(respData);
				JSONObject json = new JSONObject();
				Map map = new TreeMap();

				for (Object object : jsonArray) {
					json = JSONObject.fromObject(object);
					String a = json.getString("a");
					String b = json.getString("b");
					String c = json.getString("c");
					if ("0".equals(c)) {
						map.put(a, b);
					}
				}
				List list = new ArrayList();
				for (Object object : jsonArray) {
					json = JSONObject.fromObject(object);
					String first_level = json.getString("c");
					if (!"0".equals(first_level)) {
						String second_level = json.getString("a");
						String second_level_name = json.getString("b");
						String first_level_name = (String) map.get(first_level);
						logger.info("插入数据：first_level=" + first_level + ",first_level_name=" + first_level_name
								+ ",second_level=" + second_level + ",second_level_name=" + second_level_name);
						Map mapnew = new TreeMap();
						mapnew.put("first_level", first_level);
						mapnew.put("first_level_name", first_level_name);
						mapnew.put("second_level", second_level);
						mapnew.put("second_level_name", second_level_name);
						list.add(mapnew);
					}

				}
				merchantMapper.insertBatchMerClass(list);

			} else {
				logger.info("=====验签失败=====");
			}
		} else {
			logger.info("=====没有返回data数据=====");
		}

		return Result.success();
	}

	@Override
	public Result getMctClass() {
		List list = merchantMapper.getMctClass();
		JSONObject json = new JSONObject();
		Map map = new LinkedHashMap();
		JSONArray jsonarray = new JSONArray();

		for (Object object : list) {
			JSONArray jsonarray1 = new JSONArray();
			json = json.fromObject(object);
			String first_level = json.getString("first_level");
			String first_level_name = json.getString("first_level_name");
			map.put(first_level_name + "_" + first_level, jsonarray1);
			JSONObject jsonnew = new JSONObject();

			for (Object object1 : list) {
				jsonnew = jsonnew.fromObject(object1);
				String first_level1 = jsonnew.getString("first_level");
				String first_level_name1 = jsonnew.getString("first_level_name");
				if ((first_level1 + "_" + first_level_name1).equals(first_level + "_" + first_level_name)) {
					String second_level = jsonnew.getString("second_level");
					String second_level_name = jsonnew.getString("second_level_name");

					jsonarray1.add(second_level_name + "_" + second_level);
				}
			}

		}

		return Result.success(map);
	}

	@Override
	public Result getImageInfo(String filePath) {

		StringBuffer sb = new StringBuffer();
		if (filePath.contains(",")) {

			String[] imageIds = filePath.split("\\,");

			for (String string : imageIds) {

				PAImages paImages = paImagesMapper.getByFilePath(string);

				if(paImages!=null){
					sb.append(paImages.getFilePath() + "分割线" + paImages.getImageUrl() + ",");
				}else{
					logger.info("数据库中无"+string+"对应的图片");
				}
				
			}

		} else {

			PAImages paImages = paImagesMapper.getByFilePath(filePath);
			if(paImages!=null){
				sb.append(paImages.getFilePath() + "分割线" + paImages.getImageUrl() + ",");
			}else{
				logger.info("数据库中无"+filePath+"对应的图片");
			}
		
		}

		String returnImageIds = null;

		if (sb.toString().endsWith(",")) {

			returnImageIds = sb.substring(0, sb.toString().length() - 1);
		}

		return Result.success(returnImageIds);
	}

	@Override
	public Result upload(MultipartFile uploadFile, String type, String storeId, int id) {

		String path = this.savePicture(uploadFile, type, storeId);
		String originalFilename = uploadFile.getOriginalFilename();
		String image_type = originalFilename.substring(originalFilename.lastIndexOf(".")).replaceAll("\\.", "");

		String fildNameStr = ImageFieldMap.getFieldRemarkByName(type);

		String storage = "1";

		if (fildNameStr != null && fildNameStr.contains("私密区")) {
			storage = "0";
		}

		ImageResource imageResource = new ImageResource(ImageFieldMap.getFieldRemarkByName(type), originalFilename,
				image_type, IMAGE_BASE_URL + path, storeId, "1", "平安银行签约相关", new Date());
		imageResourceMapper.add(imageResource);

		PAImages paImages = new PAImages();

		paImages.setFieldRemark(ImageFieldMap.getFieldRemarkByName(type));
		paImages.setFieldName(type);
		paImages.setCreateDate(new Date());
		paImages.setImageName(originalFilename);

		paImages.setImageType(image_type);
		paImages.setImageUrl(IMAGE_BASE_URL + path);

		// 文件内容（现将图片转化成二进制，再进行base64加密）

		String fileContent = null;
		try {
			fileContent = encoder.encodeBuffer(uploadFile.getBytes()).trim();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JSONObject respObject = FileManagerUtil.upload(fileContent, storage, image_type);

		Object dataStr = respObject.get("data");

		if (!respObject.isEmpty() || (dataStr != null)) {
			if (TLinx2Util.verifySign(respObject, StaticConfig.publickey)) { // 验签成功

				/**
				 * 5 AES解密，并hex2bin
				 */
				String respData = null;
				try {
					respData = TLinxAESCoder.decrypt(dataStr.toString(), StaticConfig.open_key);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				JSONObject json = JSONObject.fromObject(respData);

				paImages.setStorage(json.getString("storage"));
				paImages.setFilePath(json.getString("file_path"));
				paImages.setRemark("Success");
                logger.info("上传图片后，获取的图片路径："+json.getString("file_path"));
				
				paImages.setStatus("1");
				paImages.setStoreId(storeId);

				if (id < 1 || "".equals(id)) {
					paImagesMapper.add(paImages);
				} else {
					// 更新
					paImages.setId(id);
					paImages.setModifyDate(new Date());
					paImagesMapper.update(paImages);
				}

			} else {
				return Result.build(500, "请求接口后 验签失败");

			}
		} else {
			return Result.build(500, "请求接口后 没有返回data数据");

		}

		return Result.success(paImages.getFilePath());

	}

	private String savePicture(MultipartFile uploadFile, String type, String storeId) {
		String result = null;
		try {
			// 判断文件是否为空
			if (uploadFile == null || uploadFile.isEmpty()) {
				return result;
			}

			// 上传文件以店铺为单位分开存放
			String filePath = "/" + storeId;
			// 取原始文件名
			String originalFilename = uploadFile.getOriginalFilename();
			// 新文件名
			String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
			// 转存文件，上传到ftp服务器
			boolean flag = FtpUtil.uploadFile(FTP_SERVER_IP, FTP_SERVER_PORT, FTP_SERVER_USERNAME, FTP_SERVER_PASSWORD,
					FILI_UPLOAD_PATH, filePath, newFileName, uploadFile.getInputStream());
			if (!flag) {
				return result;
			}
			result = filePath + "/" + newFileName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	@Override
	public Result loadMerchantInfo(String mctNo) {
		
		PAMerchant merchant = merchantMapper.getByMctNo(mctNo);
		JSONObject viewJson = MerchantManagerUtil.merView(mctNo);
		Integer viewErrcode = (Integer) viewJson.get("errcode");
		String viewMsg = (String) viewJson.get("msg");

		if(merchant==null){
			if(viewErrcode==0){
				if(viewJson!=null){
			            Object dataStr = viewJson.get("data");
			            if (!viewJson.isEmpty() && ( dataStr != null )) {
			                if (TLinx2Util.verifySign(viewJson, StaticConfig.publickey)) {    // 验签成功

			                    String respData ="";
								try {
									respData = TLinxAESCoder.decrypt(dataStr.toString(), StaticConfig.open_key);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
			            		
			                    JSONObject json = JSONObject.fromObject(respData);
								
								Set<String> set = json.keySet();
								PAMerchant paMerchant = new PAMerchant();
								for (String str : set) {
									
									String fileValue = json.getString(str);
									PAMerchant.setAllValueByReflect(paMerchant, str, fileValue);
									
								}
								
								merchantMapper.add(paMerchant);
								
			   
			                } else {
			                    logger.info("=====验签失败=====");
			                }
			            } else {
			                logger.info("=====没有返回data数据=====");
			            }

			}
			
			
		}
		

		

	}
		return Result.success();
	}
}
