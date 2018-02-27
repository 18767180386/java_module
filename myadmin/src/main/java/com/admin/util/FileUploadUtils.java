package com.admin.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.alibaba.fastjson.JSONObject;

public class FileUploadUtils {
	public JSONObject baiduUpload(String dstPath, HttpServletRequest request) {
		JSONObject reObj = new JSONObject();
		reObj.put("type", false);
		reObj.put("msg", "上传失败");
		reObj.put("data", null);
		boolean upflag = true;//上传成功标志
		try {
			List<HashMap<String, String>> reList = new ArrayList<HashMap<String,String>>();
			request.setCharacterEncoding("UTF-8");
			String fcliname="";//文件名称
			String fsername="";//服务器文件名称
			fsername = UUIDUtil.getUUID();//服务器端文件名
			//文件条目工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			@SuppressWarnings("unchecked")
			List<FileItem> list = upload.parseRequest(request);
			System.out.print("----"+list.size());
			for(FileItem item :list){
				fcliname = item.getName();
				if(fcliname!=null&&!"".equals(fcliname)){
					//获得上传到服务器的路径
					dstPath = Environment.getContext()+"pic/upload/"+dstPath;//服务器端路径
					if(fcliname.lastIndexOf(".")>0){
						//创建文件目录
						File file_path = new File(dstPath);
						if(!file_path.exists()){
							file_path.mkdirs();
						}
						//拼接服务器文件名
						fsername = fsername + fcliname.substring(fcliname.lastIndexOf("."));
						HashMap<String, String> map = new HashMap<String, String>();
						map.put("fcliname", fcliname);
						map.put("fsername", fsername);
						reList.add(map);
						item.write(new File(dstPath,fsername));
						upflag = true;
					}else{
						reObj.put("type", false);
						reObj.put("msg", "上传失败");
						reObj.put("data", null);
						upflag = false;
						break;
					}
				}
			}
			if(upflag){
				reObj.put("type", true);
				reObj.put("msg", "上传成功");
				reObj.put("data", reList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reObj;
	}
}
