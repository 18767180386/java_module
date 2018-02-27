package com.aiiju.common.pa_utils.platform.util;

public class StaticConfig {

	public static String privatekey="";
	public static String aes_key="";
	public static String publickey="";
	public static String url="";
	public static String upload_url="";
	public static String open_id="";
	public static String open_key="";
	public static String lang="";

	//商户
	public static String MERCHANT_ADD = "";
	public static String MERCHANT_EDITSAVE = "";
	public static String MERCHANT_VIEW = "";
	public static String MERCHANT_LIST = "";
	public static String MERCHANT_SBCHECK = "";
	public static String MERCHANT_CHECK = "";
	public static String MERCHANT_DELETE = "";
	public static String MERCHANT_MCT_CLASS = "";
	public static String MERCHANT_CITY = "";

	//门店
	public static String SHOP_ADD;
	public static String SHOP_EDITSAVE;
	public static String SHOP_LIST;
	public static String SHOP_VIEW;
	public static String SHOP_SBCHECK;
	public static String SHOP_CHECK;
	public static String SHOP_DELETE;
	public static String SHOP_OPENID;
	public static String SHOP_PASSWORD;

	//合同
	public static String CONTRACT_PAY_LIST;
	public static String CONTRACT_PAY_FEE;
	public static String CONTRACT_ADD;
	public static String CONTRACT_EDITSAVE;
	public static String CONTRACT_LIST;
	public static String CONTRACT_VIEW;
	public static String CONTRACT_RELATE_SHOP;
	public static String CONTRACT_RELATE_SHOP_SAVE;
	public static String CONTRACT_SBCHECK;
	public static String CONTRACT_CHECK;
	public static String CONTRACT_DELETE;

	//收银员
	public static String CASHIER_ROLE;
	public static String CASHIER_ADD;
	public static String CASHIER_EDITSAVE;
	public static String CASHIER_VIEW;
	public static String CASHIER_LIST;

	//机构
	public static String AGENT_ADD;
	public static String AGENT_LIST;
	public static String AGENT_EDIT;
	public static String AGENT_VIEW;
	public static String AGENT_FEE_EDIT;
	public static String AGENT_ADMIN_LIST;
	public static String AGENT_ADMIN_ADD;
	public static String AGENT_ADMIN_EDIT;
	public static String AGENT_ADMIN_DELETE;

	//订单
	public static String ORDER = "";

	//文件上传下载
	public static String ORG1 = "";
	public static String DOWNLOAD = "";

	static {

		url = "https://api.tlinx.com/org1";//生产环境API地址
//		upload_url = "https://up.tlinx.com/fileup1";
	//	url = "http://openapi.tlinx.cn/org1";//测试环境API地址
		upload_url = "https://up.tlinx.com/fileup1/";

		lang = "zh-cn";

		//
		MERCHANT_ADD = "/merchant/add";
		MERCHANT_EDITSAVE = "/merchant/editsave";
		MERCHANT_VIEW = "/merchant/view";
		MERCHANT_LIST = "/merchant";
		MERCHANT_SBCHECK = "/merchant/sbcheck";
		MERCHANT_CHECK = "/merchant/check";
		MERCHANT_DELETE = "/merchant/delete";
		MERCHANT_MCT_CLASS = "/merchant/mct_class";
		MERCHANT_CITY = "/merchant/city";

		//
		SHOP_ADD = "/shop/add";
		SHOP_EDITSAVE = "/shop/editsave";
		SHOP_VIEW = "/shop/view";
		SHOP_LIST = "/shop";
		SHOP_SBCHECK = "/shop/sbcheck";
		SHOP_CHECK = "/shop/check";
		SHOP_DELETE = "/shop/delete";
		SHOP_OPENID = "/shop/openid";
		SHOP_PASSWORD = "/shop/password";

		//
		CONTRACT_PAY_LIST = "/contract/paylist";
		CONTRACT_PAY_FEE = "/contract/pay_fee";
		CONTRACT_ADD = "/contract/add";
		CONTRACT_EDITSAVE = "/contract/editsave";
		CONTRACT_LIST = "/contract";
		CONTRACT_VIEW = "/contract/view";
		CONTRACT_RELATE_SHOP = "/contract/relate_shop";
		CONTRACT_RELATE_SHOP_SAVE = "/contract/relate_shop_save";
		CONTRACT_SBCHECK = "/contract/sbcheck";
		CONTRACT_CHECK = "/contract/check";
		CONTRACT_DELETE = "/contract/delete";

		CASHIER_ROLE = "/cashier/role";
		CASHIER_ADD = "/cashier/add";
		CASHIER_EDITSAVE = "/cashier/editsave";
		CASHIER_VIEW = "/cashier/view";
		CASHIER_LIST = "/cashier";

		AGENT_ADD = "/agent/add";
		AGENT_LIST = "/agent";
		AGENT_EDIT = "/agent/edit";
		AGENT_VIEW = "/agent/view";
		AGENT_FEE_EDIT = "/agent/fee_edit";
		AGENT_ADMIN_LIST = "/agent/admin";
		AGENT_ADMIN_ADD = "/agent/admin_add";
		AGENT_ADMIN_EDIT = "/agent/admin_edit";
		AGENT_ADMIN_DELETE = "/agent/admin_delete";

		//
		ORDER = "/order";

		//
		ORG1 = "/org1";//文件上传
		DOWNLOAD = "/org1/download";//文件下载
		
		open_id="txaP05aNb3c92c770b555b5d7d9cb35a";
		open_key="zAFEIB5vWRr82pDKPQHIqQdTFxlA2BNB";
		privatekey ="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDNuvaRQLWJqYZ+IrdbJckcBdu0fsdAblCDsGls+EGQcYA0AN2vt4MLyObfOyxSLT//mV9SxfB2FjRjDeFEFpikdq1ZTOMmlH+upgHAXdGgARaxF6s2qhQHVn2MtBjYUz2LsQyFoCXoTlYEXc90MPfBbSe+2N4cQ/he3RajHciA8F0pmWOfWhEWstmQ/vClAgxtr8KaBfaktoSJRFcFMVydW8pap9zP3X28WIik2WNoCCGys5o3Vtfx+sRb09JwKVEeuY+N1FHgU3Js/Zq0UNkcniwfhFNAlzYwQCwVfszADlp/zwS/cWHx0nOHuPGzQcxe3Je7RaUB7y7RvPYiqcKRAgMBAAECggEAfyUYM4bxawXJZ/Ueqoc8tkPsl61uiSRkQC7ryPvY0WNEH1+Von5g0Ay2mqngZ10t21K3ddl9Fg39DGbMnJ72cVYjJjh9fh9ylcZk+LVC04c4tfPxzZPpUuuiJi472J4BQG8IVSE3rE2VnZc4mg+VXwaN9MXk50Uen8o1rlO+0egejJsi4Ey09pOOdLxOn4EUghHuuyq9uJ5+MXGWwYmw9wCmzxHcIai8Rfke01lHMqaHB+rFxbjjfW9OCyblJBa+KX2wb+KnzCNmdMBjt34iD392YzTnzFm9Z95O8JnErF5r+zL811uPd6mVqJ0W7yeN4RZtta28ptOjDSvF2MlFLQKBgQD5xuA8F4/lnrNl0YLjYvt8kUOUWllLk9dJL15R2QpSlOR+KKATuTnHF5ujd1OO8hzmRbQurIIlgvhc86M/il7DLnqyClDF56Y9iPvUnTW+WuAf625gv56Pu/zVcbMVQ5NvQAh1Ee69ZNH+t8iqL6wJb++XqPBAqPYasgnwmL3ucwKBgQDS2yZtRK5bnD/DYnrJEShBrnt1Z43Rh8J2RxvmwEImqr3Pf+n4aCmTfzd+4JN8ME5w5p/ugBaOM85vAFmuPYOzKJTP6ib1oGJOAEkxVM0ppnjrvk0OTMELN+XG6WsK7Cz/U1M/2o95ezpoOHTPlKJ6ZrqyUN9NuqrbTBiCljHl6wKBgQDZczE+P4kP75Tg/IAFs+ObM9/wpKN+r4vKdAmaGejpsiQmTPnnkebiFAR0UGsXVGKiemZ+B8MaIyZTzev+YllTdQBuRZrCYISSp9IN7HugZ/8c6triMZA6M//OVrkAtx8AyG9UdDMzATXsmlWBDBWq9Z9RmkrnD/GJlhwfMOqRTwKBgEFey/UIW/3u3R3YBDUd6psiWwg1CJSzQCinNxE8/cU7HgmcoI7wzCL8R9pACblwyIDcHy0z7Mo9aXykYSlRzOjYZfpnn5h7Fr8T+50dq1WEVOzRXNZYGWk+9S/L4fHK8tBdpuue+OgyuhLhneVVMWI9QxhRVkmk25+NYfPqWKHtAoGAQ9t3c/0Y/Zg24+LmFK+n1pWut5g92rw9Y+Xj8GVDqbUgC/gaEBSBltc+xxGEtPm3OmeMZSO/XrxKZjH4QUgfDqammLV9LtT1D4TPlAbRuj4BW4S+Pq9J7DEiP3LxYE3ld59nDGut3RzvbRapyeYH3kqGUx3PiWFLJne5JK4J2vk=";
		publickey ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzbr2kUC1iamGfiK3WyXJHAXbtH7HQG5Qg7BpbPhBkHGANADdr7eDC8jm3zssUi0//5lfUsXwdhY0Yw3hRBaYpHatWUzjJpR/rqYBwF3RoAEWsRerNqoUB1Z9jLQY2FM9i7EMhaAl6E5WBF3PdDD3wW0nvtjeHEP4Xt0Wox3IgPBdKZljn1oRFrLZkP7wpQIMba/CmgX2pLaEiURXBTFcnVvKWqfcz919vFiIpNljaAghsrOaN1bX8frEW9PScClRHrmPjdRR4FNybP2atFDZHJ4sH4RTQJc2MEAsFX7MwA5af88Ev3Fh8dJzh7jxs0HMXtyXu0WlAe8u0bz2IqnCkQIDAQAB";
		
	}
}
