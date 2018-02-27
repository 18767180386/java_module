package com.aiiju.common.pa_utils.platform;

import java.util.Date;
import java.util.TreeMap;

import org.apache.commons.lang.RandomStringUtils;

import com.aiiju.common.pa_utils.platform.util.FileConver;
import com.aiiju.common.pa_utils.platform.util.StaticConfig;
import com.aiiju.common.pa_utils.platform.util.TLinx2Util;
import com.aiiju.common.pa_utils.platform.util.TLinxAESCoder;

import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/** 
 * @ClassName FileManagerUtil
 * @Description
 * @author zong
 * @CreateDate 2017年7月26日 下午3:09:52
 */
public class FileManagerUtil {
    static BASE64Encoder encoder = new sun.misc.BASE64Encoder();
    static BASE64Decoder decoder = new sun.misc.BASE64Decoder();
    
    public static JSONObject upload(String  fileContent,String storage,String imageType) {
    	 JSONObject respObject = new  JSONObject();
        String timestamp = new Date().getTime() / 1000 + "";    // 时间
        try {
            // 固定参数
            TreeMap<String, String> postmap = new TreeMap<String, String>();    // post请求参数的map
            TreeMap<String, String> getmap  = new TreeMap<String, String>();    // get请求参数的map
            getmap.put("open_id", StaticConfig.open_id);
            getmap.put("lang", StaticConfig.lang);
            getmap.put("timestamp", timestamp);
            getmap.put("randStr", RandomStringUtils.randomAlphanumeric(32));

            TreeMap<String, String> datamap = new TreeMap<String, String>();    // data参数的map

            FileConver f = new FileConver();

//            String s = "MzY3bDllOHNmbDk5ZjgwOXhoNzhuMzg1bHFlMWh0YzJia3FzdWRybHh3dzcwZTVkaGsxNGZzZ3o0\\r\\nNzhiNzNrcXV3c28ya3poNnRjdHY1amd0bHB3aGIxNzVzeXpkZGdmMXUxc25hZGNzeGVqcWRkOTI0\\r\\nMm14bmY4dHoxaTViemxvbGppemE2dWsxNG9jdzIzOWd6dHh2dWFwanppNm1sNWV3MWo4Mzkza3dr\\r\\ncm1xaG10NDhjZzA0dTlneno0ZXM4MDc0ZDljNm54bzN5amV2dGNtNGVwY2JyaTNvOGZwdHJpdjE2\\r\\nOGIwNDVnNmhxbHJtOGFoNGh2OGxxbjNycGtxYzQzZDR0ZW9tcDkydDhmaDd0cm9yaXAxcTJlbmEx\\r\\na2JncG1pcHB0dXo4MjNrcm1qajU1aXh2em85dGprM3E0dHRkNG4wZTB4ajB4ZnZrMmY5NXRrcXYz\\r\\nY202aXcyYTB0NThzeTNvaDYyNnR6dGV3M2htNjljMWY5bDBwY3J6ZmRhOGhkNWpkbzB4YXRpYTZt\\r\\ndHkzdWxqbXYyZm43NjJzcmR3aG9pbDJ0dnRyaTRra2E1MWdzdGhlOXJ2ZDdocTdydTI0MmwwbWFw\\r\\neHhmbWpjamw3NTFubjF1MmEyZ3kxODl0cTloZHkxeWJvdjF1Z3MyeWdkZjhxNXhvYjQ3c3FlbXo5\\r\\nZTBnNWt1enViNnZ1dWZ4eDg1cjJ1N2tzbmZ5eGk4MDJ3dDY2ZjdtZ2pwb2lkMXBuY2cyZXF6dGV2\\r\\nYXdldGh3eThnNW1zbXVvbTdkMzN2NHVrNzdtM3lqOThjOWJlMTAxbHUzZTQ1emFicGE5MmZyaHNw\\r\\nMDJyMjhnemVsa3VvcW94emN1cmNjbnh3d3FlYzZvaGo2aDF2ajl3c2RndTNqeXNjanZwdWRiM2Rz\\r\\nMXF3cW00ZGx1YWFiNnRlYTlmNW1yMno5cmRmYnNwZ3IyNHJ3c3I0NnRwN2FjemgzYmhleHprazc5\\r\\ncjQ0aXIxanJ0Y3lodzNwdXc2bGo0aTQydWE5bWd6cGNubWF4bW5uOHUxa3psdDNhaGJvNjQ2ZHpt\\r\\ndGg3bGpicmU5YW1pdjF2bW1wNHRnNmNhNWtubmRteWx0M3hsemM1NDMxejVuY3YyaG12NzF0ODVu\\r\\nYzBiMmJyNHI1NXk2ZWw3c3F1N3MzYXgzenQxZXZ6aWcyaDBwYzd5Mzg4NmJwaDI4eHM0aGYxZnBw\\r\\ncGs3Mm5uZmFqMHhpN2E4djZrMWZ1Z3hpcG03ODU4enM2bDM5OXY5b283cWlmeGZ4NjhscmU3bHo5\\r\\naG84bTNyMm16aGR1bms1OTliano4dnYzdmhqamM2bzhwOG82YTN2eXZmZHJuNmRjNG80OWE4ODJ1\\r\\nN29iN3JrYzJvdXdzd29kanNqYXg3b2NweWkxbXhsazcwYjNzMGpnZHZ4ajZzYWVwZzFldXBjaDRm\\r\\nYXJxeThrODJmcnc3YnRoYWdiajZqcTBqcDZ4YnVrMDBoZG45b2Vta2h4cXpiNXdpbTcyem95dTZz\\r\\ncWx5NGMza2V0MTdyem92OXk1ZWo3YWNtczlmcGV5dzFxbmc0bWM0bHgwMHhnNHkwdDFxaHN6bHpl\\r\\neTNwenpmaG8yZWcxZWh3bzR2d2hwcG1oY252aXpoNzVkOW16NHpib21pNno3Mm4wbzRmaXZ3ZDZr\\r\\nczRzMXFpczhoYmE1ZDd3cTBpZzBvODhoZGZ1bGZ6Y3Rqd2libWVyOWY0YXVsbXYwaWF1eGoxd2My\\r\\nZW1lcGVzdzhhNGpzZTRyMXVzbmMxaTg1b2lyc3V0Yzdhcmc0dDJ5cnc3aXcyMTh0emVpaXVweGhy\\r\\nNDZxNmd5MWszYjdha2VicXRsc2U5MnkwMWVhaWkwMnhoejZzZXJnYnA3dDg3bjl1Nnh6ZTkwcmEx\\r\\ndnpnaHl6dHZrbzVjeW1uZzdiMGtzMGFqdWY4eHgxMHRxMW9iaHk3eTF6aG5kMWVtcGxycWQ1YTdw\\r\\nY3JoN2o5bmw2MWUybGMxM2t3dHJoeHFuYjBvMHE1bmNkNDZxdmtxb3kwZjM3aW5lY3pvNXlmbGU2\\r\\nbTRnZ240YXdtZzc2bGM2YTVkZm8xd3R2dm9tZmlxNmg4bnN2c2E2aW9xNzJ1aXc3N25uZ3htZGt5\\r\\nbG9hMHBlbnVqd25yYThyc212d2xvZjZ4Y2Jvb2t0aDZ4YnFhb3NtNG1kNDZiaHJjN2g5cXcwZzF5\\r\\naWY4NG15d3dqaW8xbGF2OWhseXc1eXRyMWQ3djU2azVia2YyOGxsc3A5b3J6ZHNjcWQzanlyeGVs\\r\\nbjRsczVrbDZ1Y2JmdnRwMm5tY2R0NG11dHJtdXFkNWJianB3ZW0xcTdsdno3eHJqb2J0d3hmOGh6\\r\\nejg3bGQzNzRlM205MnRmMzI4c2psODgwYzRxd3Y1MmRsbWYxMDFucmgwZDlmN3draDUyMXEwNjN1\\r\\nbjQzeDA5eWk1azN3b2x4dTNhaXE1djFycTAzZXE5dzBsazJ4YWRvd2N1OWxoZnY5ZDI5Z3V2bjY0\\r\\nZnpsc29taWd0aWU0aHpyaWR2cGZldWhxcGI4emRjaXNtcHoxNWNkMzd2NnkyOWg0b3Nvam04eDl3\\r\\nOXZjaHM4ODlsZWZ6MDZuMzFkbDBrNGp2aGUwYXFzbm9hNnoza2hjOGNjY3k2NGgxdXJmMXBxb3Bl\\r\\nc2d4bGRhcDhhYzlyeG9zNnQ2eGwyOXdkem10YXJoa2FycmRkd3dxbnpueDk1c2gxNGRoZmJ4OW5p\\r\\nYmVqeHRkeWZpdmZ2ZzdseW42MDM4MHdqc2Q1ZDVyeGZqZ3A3YjI5M25pcmhibnJpbnZwOGZqemM4\\r\\naXRuM3J1MThwZ3VxZnBlamRkNjhscmxyOHF0cmpyYXNwYnppaHN6eTJjcnBoNzVmNzhpcmZ6bGRv\\r\\ncm15MjA4b2d5OTB4eGNxbXdjYXBjOGo1NnR0eWRhZzM1ZGJ0amtsM3Rha2p3enR6YXVjZmQ1cWF3\\r\\ncGE3dDNvOTJ6aHpxc3Y3eDJ6c2l2OWF2Zzd3ejByejVlbGozdG9iaWpoM21hdWRleTE4amZmOWlx\\r\\nNTIzZ294ZHBkdWlxamN6bHhuYjlwamF6ZzdmaG56a3Zpbm0xejZpMWhmcmtkMXVzYXZ0M3k5dXZ2\\r\\nNG9ya2dqd29pamRmdWs2YWx5NWZhaDd6aWQwcWUzdjFvZHB4d3JmbThxaWg2MzRtc2s4ZGlndHFn\\r\\ncnozM3Vwbzh5Z3hnNXh1aHpta2VkbGUxY2Y3cm5tOHMzMmpydXZ3MXFvNzF1eWFlNmllaGo5emNj\\r\\nZ2wwbTQ4aDU3dWlmbWxoY3FlMG9jaTV2eGtqYXBrMHVsOXM2Ymp6ejM3eGpmNGNvam5iOGE5Zm13\\r\\nZHBzaHNhNWluNjBoZzRubWx3cGRvMHU4MGJ3NDM3azNqMXo4d2l5Y3QyOWpzd2UweGNsYXB6ZDd5\\r\\nNHY3YjBhYjR6eWEyenRrZWowYzV3cnhoNTk1YnBqZnhveXNpZDF4NHRvdTQ0bzY0amtoZmhkajRw\\r\\naW0zMHR0NTZ0M202emVrZXBvemp6NGg1YmN6ejFqM2F0eW9vaTM4aXdoNjB5dTc4cm91amJteDd3\\r\\nZzN0ejA3Y296MWk2aXh0dHRlZzhwZ2VzbmR6aG9mYjk4cWt1cHhqN2N1cXJxcGVkamZ4YnR0anNi\\r\\nNDNhNDYwdmc4OGd1a203aTRnMXJ3bnBmbWhxcW80MHU5Z3ZuOXY5M2NlZWJoMXk4dGJlOGx5d2Y5\\r\\nNHkyaGxrNHc0MHk1a3E2OTdqY3Bwa3RnZ3p1ODR5eDNkaTh3bzZyOTBhYnhoMXE1Mnpkem4xM2Ni\\r\\nbjVzcHNzeXc1eGo2dG0xYWpyMTcwNm5zaDlxNDJ6OGNsMzFheDBndWFqNW5zOGZqcnVqOHh4Y2ky\\r\\nYmJiYXV5eWc2aXE5ZW5wd3RoZWw2cHZjMGFyeXQwMjNvbGd6Y3BnOGxiN2UzODVudWJ4MnVxMTc2\\r\\nd2EybjNnajJkd2YzdGVwM25hdGpyM2ZqZHR2eGJzcW9rMndoNmVoZnA4d3h4OXhnMzJzZzhuazZ5\\r\\nd3d1cHk4aTgyZWIzOXpzaWF5cDByYzRjdjB2aXB3c3VjYjV2enNzaG0xMXhrc2xuamMxYzFsazM4\\r\\nc20xODF3eGpsdjFpdDR1cTUyOTgzeDd4anluZmZjYnk4M2Z4bnNzeHYycWMxM3B5MmhtZmFwNTZz\\r\\nODVwYnhrdDI2NW4wdmxlYWJrdTU2NmNhM3NqYWhrbG04YnRsb3Zib2d2b2MwdmIzbzk1eHRxaGtx\\r\\nZnhydm5pYTBxaWljZ2J4N20yZ2hoOW1oZnA5dXhuMHQ0ODA3MXMwZGc1NjFlZmkxcnRuYnZiNjFw\\r\\ndzJqOXU5NGY4Z3dqN2gxNXhpbnZkaG5iOXB5MDcxMHpydmtrNnlwNGJiaHFuajFnaXEzY3BsZTkx\\r\\nYWFiM296NHl3eXFibmlzMnJ0cmt4cHZ0ZWl6cTB4cm45Ynk1eWhvZ2djaTEzeTY0MjR2cGs3bzEy\\r\\ndGN5MXdoZHByYTE0YnZ5cHpzMHl3Zm10N3dxNXFtMnM0bWNudGcwczJ1ZHF5ZWJ2eTBkeGoweXhn\\r\\nNm00bzZ0bzVhbWg2OGh5M2JueTY1NTEwYTRjdzgxZWhxNHMzM201d21mN2JyN3QzYmRyZWxmaWx2\\r\\nbnBhZDNwdXJoeHA4YmZhMWoxMHlpcmFrdDc3OWllZjhncnV5N3N5eDZjbHdjaHU5M2U1dmtiNXVx\\r\\nZW50b2lrOHg0cXRrYzJ2M2RibHltMjBnZjdxdHN6Y3RjYnNhdno2bnZ1cDRpMGd6ZWEyNmk4aDdo\\r\\naWRrZnBlY3V4MnB1dWV4ZWhzMzU1dnhpYTQxam9iYWlnc2R4OTE5cHI1YnJvdmM2ZWI3b29zN2hp\\r\\nMHBtaTNyMG9vcWFkajJkYmtvdzNteWY4ZnAyNjNmb25qd2FiYXAzdm11YmZoeW16eWN0Zmdxa3h3\\r\\na2czNWE5MXltdDZ5aGdlMTdtbTQzdWdzYjFqdDNqNGY2ZDZzNWlhcTJtbXpleWpuc3l2azkxbDI3\\r\\ncDc5M29rbDI0bG44dWlucDgza2N5dzRjMnNueDU4NXpyZDZ0MmVxdDA3Z2pmYTF5ODBydHYzZDBi\\r\\nbHNla2Uzb3NxdGI2eWFmOW9zczY4czJ3czlvOXBzZ3oycHBxNGhnbHFmbzZ1Y2NxaHozdW45YzNx\\r\\neHhmemVlOTl3a3poaGtheGprOGgzcmd4N3o2aDFtenc3MDMzemFxNmhmNWE4aG1iM2U1aTY0ZHFw\\r\\ndXhieWpoNXd3OGwzY3pqMTJ1YW0wNTA5NXUyajR0ZHF0b2RzbXRuazZtaG4wNXdkM3A0bjlpdHp4\\r\\nZGR2am5ncXB4ZnhlOW14OXZxZThraW55ZXY2enp1cnE5NnFicnRkeXU5NG5hMDh5c2dodHg5eWEy\\r\\ndGUycm4zeHB3ZDZzajNsMmlocGZxYWZ2YnUyNW85NG1wYnh1ZmVxaHh5NmZldmR6eDB1d2F4OGxq\\r\\neDU2aXlwaGZjOWprbnQ0Mnhsa3NvcHBvNjV0Y2JybnF1NnFpNm95YnR2ZWR6anJxZ29tb2t4eTRi\\r\\neTUya201aWQxMjF3bTY3aWNsY3BxcGtiNnJnbXYyNXN5ZGI2YTVxeGFncm8xZDY4aHEwZDFrbjAx\\r\\nb2ozYWMzazI4cGR5M3A0Nnp6MnUxaXU4dmI3NHBkcmhyNGtsYTYxaWZxZjl1amJteW1jbzk4d3l6\\r\\ndHJlMjYyczk4ODVzNG1hY2MwdXI1Yzd1cHNrOTQ0MnJqcWd3ZXJud3liN2hrMnBnaWprcGNpczMx\\r\\nMmRmdDFtdGY1Y2oyejlnYTNoMmowejAydzViejNranVxZGFibTFhZXdoNnJwZWs1djAzbWJwNTZ6\\r\\nbmhidjBremJ4d2QzbGs2bWxyb3RsYzBpNzVmemI3azNhMTE4bXY4MmFibDJ6Ym9maGplYWh0YzRq\\r\\nMDNvY3hhY3VibTNncm01eWR2bjdubHBueXo3MjRvbmRneDNkdXZ4dm4xNHkzZjhxdjdhb3k4dHZk\\r\\nbnViOHpkd2UxOHd3ODY2bGJhNTRxYXVwc2hidDdsMXBqaGd1dmdnMDNyY2F2bGFpYmp5bXZpYWRs\\r\\naGRzdmxjdDVsaXQ0Njc0dDJpZTcycXVtYjAxd2F0a3Q0M2FhMHV5YW5ybGFlNWRwMXdzNDFibGp3\\r\\nY2t6aW1tcnVidWQ5M25wdHhseWFraHhuYXk5aWthODlqNmN1MDZydDFjZjFmanMwcHV2MmZ5ajN4\\r\\naTR2YnNtaGF1enIzbGMzejd6dnduZXlvNDlkN2MwdWx4MjJ2ZGRoZ2Rra2RlNnV5amtvdnp3bnpx\\r\\nd2Zsa2RjY21mcWtybTNhYms0YmFsazVqYmQzdWRxbzNheHNpM3I0NGxieDl4Y3Z1OW84aXp2YjM4\\r\\nMHRxdTBremZmenYyNWlqM3NkZmF1dmZyY25sZmF6dzRkZ3V0a3BrOGIyb3NiNDI3a3pwNXRidmtt\\r\\nNnl0czl2bjE3NGcxMTJlZnV4bzR2cmljczVwejZmanFxanJoeDg4dmFrZmx5amt3MHh0bjhwZ2F4\\r\\nemlwZ3NscGt2cjc5empjbXNodmNwcjNpaDBzdmk1NXEzZzM3dmFoeXRhbmxxYjF3azByYmM4OHQ5\\r\\nMzgxNDJpNTUydXR5bmw1NWdweXl3bWpkc3Uya3B2YmVjMzFkMGRvY3F4a2swc2I4emhkbndqMGl3\\r\\nOTExdGZoM2tnMGNpam52bmthcHI1cXhtbXdwODZwZWU0OWZwM2owYm01MGc4bDg3Y3FqNTduNXA5\\r\\nbzRtN2dlODMxejBrczRsM3dtdzNiNmZlcXlnMW14OWI0enF1ZGpnbmZiYTU4NnF5eHc2dHhxaHh1\\r\\nbzR5OXl5ODE4d2FlM3pqc3U2YnJzMHZkejFubjZ3Z2x3MmQ1b3d1eDF4azRzbWU1dXc1ZzB0OXNw\\r\\na3o2em9za25xNWlmZmY2OGNsNHpwZGFuaWRtcjQxYmZ1eXhueDN6bHUzbTd2ZXplbHZ5ajh6OGhu\\r\\neTZic2wxaWZsNmU0MDhka282c2N6c3p5ZWc5OWwyYWxxMTMwbTI4a2c1MGJmcHZ5ajFpaDdvM3Vu\\r\\nNnRxaHJjcmxxcmoxaTZwdXQ4anNveHVzdjZyaHA3ZDk4aDhoNGF0bm56cTBubDdmM2NhZzFha3I2\\r\\nMmZrbGl5bWJzYXY2ZXJxOWdteXp2NDBraTRzb3U1NW4zb2Q4NXVyNzh0dHJidWJnd3lyOGprbG1y\\r\\neXZ6YXE4ZHc5c3VibjN4MzU2bnk4ZTM4M2VtdXR1cTU5amgyZWd1MG91YzR6dWRmdXkxcmhuNGk0\\r\\nN3BodnhzdnFuYmZrM245OWloNnJ1b2NpMnVmZjVjeXgxcXN2YjFmZndwZWszcGprdmduYjh6cXJi\\r\\ncGRtZG5pN3YzcHRicnk3ZnE2N2dxMG5vMGV3OGxuZ3Z6a3E4cHV5Z2h1NHBvNWt1bnp2ZXhueXA2\\r\\nanJxdmZqbXJ3bXp6cWNsamRtNDFwdjkzZTlhMGNja2xvbzJkZWkxaGEwdDJvZXRubzBpeXU4cXE5\\r\\ncDJteWRpdHQzNW9xamV3cGZpdGVhejNuNmprZWYzeGcxZnQ2cHdsNTE5cnB5MnQxcHB0a2lmdHAx\\r\\nbTRsem5oeDkzZzVocHBzdDM1bzZjYjF3eGZzdmJzaWRsYzN0enZ6MDl1MXRyZWNidGFncjE5MnU1\\r\\nYnc2OXA1N3B3b2hla3RsOHp2dWt0emU5dmJ6eDdvMnB5amZiNHNlcjl3bXpkcGprOHM0ZDByZ3Bk\\r\\nY2U3dGtzeDhzZmtheXI1bDN4d2hqMmZqNzhma3ZzeXBhZm45dnJlZ2Qzdzg5NDl5NnkxMHl2NDA5\\r\\nMXdjem5vb3kxcGg3aTR1aXFwYjRjMTF3N3Ayc2ZudTIyZm5qeXNkbXQ5MXBlYzdkNTM2MGdsc3Uw\\r\\nb293aTkybWpxamwzeWcwa2xjcWNkaHg3ejd0NHd1ZGdmMWEyMno2YW9qbXdsbHN0NTNsZXRjZDV3\\r\\naDR0c3F2dGtrb2ZlODF6bjRoMzFkeXRnc3BtOXJrMDllMjdrYWl3NmYwczJ5YzRxd2k0anNnZm14\\r\\ncjlkaDVrb3RrcWNmNGQ4Z214OWFhMGIwbzdwb2Fhb210ZnIyZGd5cG0xeHk2M2xsZG1hMmJjcWpr\\r\\nMHVhZWpwZmwzank3aWRuMTV6OXM0dG50d3N6MzJyZ2tkbThtaHdua3YzNThqemZwOWJmZjRuNzEw\\r\\neHcyaXRmM244enNtZjI5ZWtlemRpNGZiM2RqZ3ZrdDljb2tyOHp4Z2VncnFsbXE5czNpZTZ5bGxx\\r\\nNGRvb3dhNzI4aWN6bmdtdzBkb2p2bDEwNjh2Z3kwMng0a3ppa2pjbjk4YXA4NWI4YjRpZnJwM21i\\r\\ncDV4ZDNwNTBmM3VuYXJmaHc3bDI0d2Z5eDJ4cXRoeW1vYmJjdGZpc2plcXNudTNrdDdsd3FiYWcw\\r\\ndHM3OW85d2oyZnczMDV6eGRuN3hlcjMxdTNtMnVnaWhiOG9jNHloampscDNqbTIzNHAxdjV5OGo4\\r\\nN25vaTk4c3huY2NtbjZjcW1mYTZjM2dmeWQ0dmV1Y2s1MDhiOWk5Y3FmenV3MDJucjNxZGxhNGJ4\\r\\ndzB6d2ljeHp6OTMzcHR5dXk3OXFvbWN5MmxvdzIydXVhbDk0OG9qamN5cDQ1dTZ2MW96bjhrYXY3\\r\\nbXA5bDRrcTRxbGtoZ2J3bzI2NDZxZHZxdTIxc3R5NjJmM210Nzh2MWRkNHZ0bzc1ZXFlemx1enV2\\r\\nZzBoeXgwbnVwb2twcTh1bGZuOXpsZHh2OTFubW91MGNjN3FpejJhajNzYnN0dXV6Z2N0ZHE0aXI3\\r\\nZnUwZ2p1emprZjA0aDBmYTkybHpheW9iM205enFzZTc3ZTR5NzVtc2pzZnNrOGF2cW8wbnZyY2ly\\r\\nczJjc2VlenRuZjl2ZmQ3dWc5Z3Z3bnZycHZoaXNkZmFzd2VkbzJ1czdlczhqaDF0aXhvbjduZTJ3\\r\\nY3JoZ2QzcjVkZ3U5aXZxYjllMWwxMDkyc3l2ZWVscDV2bmxkeWdjb2Jrd2Vqam1ncjQ3Mjl4NWdr\\r\\nYWd1YXNnaXo4bnB1dmcxdjR1azRhZGUxZm1iZzdjOTY1ZWc4c2dhdGRuNmtjYjRnbzlxdTM5bnps\\r\\nMGRvbW9tbmZpZzh0NTVoenR3dXg5ejE4enRycG16dGg1bGFkeXhzYTVyNDczMTZrN3RiMGUwZ24x\\r\\ndjVsOXp0cTZqaHBmY24zdnA0MGduYXM2ajQ3djJlcnNxMjlwbTdxZGt4d2luZGUxcHV5anVqazNp\\r\\nc2c5MHpjamZleW96eG1kdndpNnFjcTB4eTRncXAzN2FsdzQ0djR0ank1azFlZHc2ajdpbnZwNzg2\\r\\nYXp1OTY3NmN2eHYyeTNzbHhza203ZHRxMHhiY3VnOXhjaWtkNTQzbmhsMXgzY2puMXNzdjQzNmY1\\r\\nOTBvODFnbzFkaGp4dDVmc3c2MXd6Ymp6NmMwc2tnNmw1MDVhaDRyZmduejd4YzB1aXN6ZXppOWdk\\r\\nZ3p4NjZ4OHFiMXl3bm1tMDlibm5sNTZxZGFuN2J5bXgxbzNva2l0ZWRrbWthNmR6Y3lmOGM1ZG43\\r\\nejZzYzF1NWdxN3o0bGx2ZHE2YW1uOTEyOWl5eWg3Y2RvM2xqZXg0OTBzdHV6ZW1kM2Nsc29pb2tw\\r\\ncDFycWl6OTB1MnpiNndleWhjbzgzcHJ1OTBjdWg1Ymg3dnlndW40aWNocTkwZXZma2Nub2o0OWd4\\r\\nYmxsa2l4cTN1eTZ4b25qcnNqcHI5a25wNjRmZjQ3M2dkaTJyeWxtZ2k3NGN4dzkzMzIydzh5d255\\r\\nMGMzZXBvY2huZ284cmJ0aXhjc21yYTEyYjJyZWo3eHJoeGhxNXhxYWd1NnAwZm5jaG85dWozZzBq\\r\\naW5xbHUwdzhkaHU0Z3ljNDcxZno0NmJvZjFnM2Vyczh0NzE0bW0yN3M1MjZ5d282bDZ0YmNwaThj\\r\\nbXc3bjNweDl5cDg4ajJmd2psaDRoNDBiNWFvOGE5OWxsNm9weW8zc255bGM5eDZhNjUyb3pxaHJ0\\r\\nNnMzajQyZ25qbDliankwdmJweHpsZDU2NjkzeGhmNTF3YThsc2l3Z3J2M3pic3N0NTZkZnE5dHhq\\r\\nd25vdHkwdHdhbXNsNXZydHkyZ2FzNDd4dzI1Mmo5ZndhODcwa3BqYmFlN3dyeTVhMDZlMnVwNXA2\\r\\ndjZlbml5NzY3NnNhYW9wMGphcjh3NmR6a2dmdnhtYnBvOWh6dzJybW13eHFzcGJ0ajVpemQ4Mnk5\\r\\nZGVtbTlsZnRydDY4YzV1ajZwMng1Y3YxMHN2cWZ6NmF3cGNpYWhpY3F6NXQydDV5dndmNzk1bXl1\\r\\nNmFsbDB6MDNwZWNqanVnanh6ZzNraXRubHpqcDMwdGs5N3l0Zm5zbG9hN2d6OXUwOHBxeXh4bHhl\\r\\nYnM2YzZwdHF3dnAyMzhodDE2czdwbDltN2h6cTFhMTluNG15Ym5kaDMxNnRiMjJieXkycGExZWZu\\r\\nYTdobDlsZm5icTAzODlkMGE3OHVqcG1jN2t4cTFydjQxOW02emdmZ3gxcGN0YXg1dnZveG85dXdk\\r\\nN3FzZ3RjMHJicWFrcnE0OGhhaHYyOHY5MmZsd2xsb3JpYjFiNnppZzk4aXV0dnA3dmx3djU5dzY3\\r\\naWJkZjB2bHlyYnliaGJ1ZXVwdGRtcjZ4NGsxYmh0eHRmdGJqM2tqb25rMHVjdjFuNGNpZmk2Y3pn\\r\\nN25sazdkcWtjOGs0Zzd2MXdiNmI1OXQ4cWpzbm93cmR4M2wwdjAzNDJwYXB4bnJqcTQwdmc2dmw0\\r\\nb3E2N3Fra2Jsc2M0N3Y5MGU4Ymp3eWZwbzVuaDZ0czhnbmlxYWtxeDRkZDRzdHFtaWJrNTRvZHU1\\r\\nODB2N2w0ZnZrMXB2bTU2Z2ZiZGRhOW5sdG1rN3hqbDhkMzFyNzF1OHB2d2M5NHVxaTZhc2ZkNHl0\\r\\nMWl4b25jOG1rcnM0M2U0cmdwZGdhNXBvdWpuem5iMGNyemhsOW1ucnR2Ynd4ZWI4MjZhemxyOW5w\\r\\na2lmYzFpM2o4MzIzZ25yOXNhdTdtamI2bWZxaWI4dWk4Y2w0NTR4ZHBuem4zcnl6MjQ0ZnA0dW0y\\r\\ncXNkeHJ4djNmdGpsd3Y3bmhrY3VwMTRpaDkzMWhsZmR4anVlbzc0bml6NThkOGR4bG1qcjk5OXQ0\\r\\nc2RkZGt5OTFjaGR6dGoxYTM5MHpyOGNxcWpheHQydmk1ejM4Z2t4YzI5NTNqOHZmMm5wcHhxZWll\\r\\neTN5eGJsNGN2dDloeHJ4NWlucjRyemplcmdncXEyYWRzYWcxcGV2bm4yN3MxaXRnZTZrejBtdnlk\\r\\nYjlmMWhrM2Q5cml2emZ2dHp0bzUxcHVhM2t1NHd2bWExajV5OHhqbXU5bTgweXFsOG53NmdzdGxs\\r\\neWcyZnpzcTNya2N4djNwaWIwd3pjY3ZlbWg5MmJnMW0wOHBxZnVraGNoN3gweHN0bXV2cTQ3ZzA5\\r\\nZjg2ZTAwYTQxM3Q3NHB1b2t5aWZmemhodmtyeTNvcHppdzZ1cDhrMWFsN3ZkamJ4aTE0c3MzczM0\\r\\nN3A2NmtjZDEwczFnZXVjdzVtZW95aWVlN3E4aHd0eGxmM2h4dnh6eHVjazFjeXV1cDBjYmtmY25w\\r\\nMDY2eDFlbXo5eXNsaDl1MTdtMDdzdWIzYnU3a2c2M2M5ZmxrdWxocDZ5aTllcGlqbXYyNWwzNDNn\\r\\nbTVteHg1aTFkcmdueHhtcGE3NGF1eHpvNjZsdGZqZ3I2Zm5nNTN0M3podHFhdWszcXh0Z2t0aG00\\r\\ndmt2eGxxNjBhcmd0ZmR1bGR0eDh3ODM4OWZ0MTd3bzl2Y3pha3o0bG9xcmJiaDJoaHFkdHYwcmNn\\r\\nbzduYTZjNG51OGo2dWI4OXU3Z3R1OWRwcXB3aWZ0cmN1dmt1c3EwYWJlaWFwMzg3cHNiMTF6ejJ5\\r\\nOHJpNmI2YzkzZDdjdG9lcHBxNWRuM2M3Mmh5bzJxN2I5amhkaGE2a3R4MXZpYzYzbndwMjBzN3d1\\r\\neDNsYms2NnhkMDcwMGZ5bHVnemFxcHdhM3cycmJmZ284c3RkazZ3aGx2ZHhoYTkwMGE0d24zZ24z\\r\\nbG4xaDY5bXZ0YWtxMHExeTdsYXU2OGFiODc0NDE3dHpha3hnYWV1b2IzdGt3aDJwMDJ5dDk4Zm9i\\r\\nY3RzMmVlbzd6cGc3N3FsZDAzcmRyNXhudzhpdnRyYjVlYmhid2ZlN29tbzdlOGMwYnRiZngyM3o3\\r\\nOWtyYnd1azNlbnZmaTBuOG94djhvcHkxdWRsMDZ1eWJxbWFscmJibHY2dzRqNmZ0bWwxOGdpMjlh\\r\\nN3IwbDRhcHI4ZzIwbDc0dmE0bWxzY21zZjdzMmxxMGh4bnViOWR1cTlnNnBrOTJ4eWdvb3p3ancx\\r\\nbDZub2JvNDE2emV6enZpcDh1MWx6MHI0Zmt5ZXJoaXNpZjdkdzRiZzh6b2dud2MzbGEyeWw5aXk3\\r\\nbjY4M2JsbDN3bWwxMWU4eTF1NWZraTQ5YmJpNGp2dWphems1cjV0NTFldHFkMHE3Zm1qamhvMGI0\\r\\nbzcxdmZ5NXphY2lzaXB0dzQybWI4M3VlcGhqbXVmd2hnaDFxemdzYmd6NGlwbzhrZmdxcmxuM3M1\\r\\nbnQ2eDg0dWtwbHd5ank2cjhjM3BxeWdsd3RjOHgxNWZueGNlZDlidmNvZWlhdmR5MjIzbWxldTNm\\r\\nNHRlenRkbTlxdjZ4NjIzYjc3Z2l4YXJwaXNlZjU4dWJkMDZubnJkOWNtNDNucnppaXBobGl3czlu\\r\\naWl4YmJvbXF3cjlrM2Rjbm0xeDlhbW1rNGh0eWI5d29zZ2hnMzRpdnRsY2l0cXBkY2Z5dmE3NW1l\\r\\na3J2NGZlc3k3cHN2dGpmOHZlZnVuMGQ1a211dzBzaGw3YjBvaHg5aGc5ZjJzZnNvbjFiaXF0dTZ1\\r\\nY2ZxdnA3bnB0aDg0enZpdXhzdHJoOHJ2NGF4MWlsczBsNWUzNTJ5bHRrMGdvZjJubDduNThkcGU2\\r\\nNHo1NXl6aGlyZWF0NGYzZG94OG5odnlkbzN2enRhOGRnMmIzNGc0Z2R4b2E0MnptcnRleTJuODNi\\r\\nYno5N3d1ejZrejdtMzF0b2ZycTNwZmtxcm02anhmNWI4aWZpMjB3cWQ1ZGw3eTJxY2Y1NWYxZXg4\\r\\nNXFmNHlyM2J3Z2pnMmd3c3JqbjRkd2tuOXo5djFkZTJlMzNxdHRzeWI5eTViN2tmcjhvaTE1bmF2\\r\\nbXQyc3o2OXducml3OXlvZzVsd216amt5eTNqOTVsa3R4bW9oZHdnMnptMjhpaHQzMW8yaWZ5dmZ0\\r\\nbGRndjlpYm12cXVqYmNuZnZrdGg1MGNkZDQyam4wMzc5dWNoMjQyYmh1emR4d2ZtdnNsang2bWNm\\r\\nbDYxbGNyYTUwZzBkNHcxZWpucmJjdnFydjZmNDFyejk0Nm56aW50cW5hZDVvcjNkODFmdXRvamsx\\r\\ndW1kZHh1dHNocmFnZWhjaGlwdjB5dGszcGpzMmU4ems0amtmYzlndWhjYnBld3g5NnZwa2VvajR6\\r\\nbm1yMzdvOGhmaHNrYXp5YzljdnBicXI2OWV4NjVlanBjMms2bXZwdGpnNDA0YjZqaDV3am9pbG8x\\r\\nbm45NHFzOXpnOWd1em56YTR1ZDh4Z24wbjI0MHBsaGV2MGs3MHQzbXE1YWxvcm9ibmUxa2p1cWJh\\r\\nN3FjaG9rYjBjdHVmN2tpejk4bGp0MzA0NDU3Zm1hNnhuOWR4bHN5OTBsYzhueTNpcm9jeWxsZjV0\\r\\naTg0ejllaTd0bWRxczVrZWVxaGF1eWhsYTRpN3N5czNia2JwNDN0ODhrcnh5ajg2dzBlaXlvd3U4\\r\\ncjM4MXdyNjE3emJtdWY1OTJqdGVwcGpyeHd4bDM1enptNmMxamZpdjF2cXJ2MHZ4dWR3a2ZuMjFy\\r\\nNHEyM29pbzlmbmlqM2p1aWxrdXJueDRiN2o1YXpuYWFsdnNqZmtwOWJoMWZsZ2tsbXFqeGZ4YWto\\r\\nN3hnaGN6bWFpaHQzZHR2cGZkODFhZDdnaGtzNXl6ejM1bTFkNDF2c3QxZmN4amo5NGcwMDFqYXEz\\r\\nbGxocG5lMGl2bTJqZTJnZnhhZ3VtdXc2Njl3ZmE4NTlhaW1udWJ0NWIwY3M4YjB5bjBtcW1rdGNu\\r\\nZmZjd2sxdGtsaGp5bjFodjZmaWllcGFuemN6ZGl0ajVxazJqb3k0aHlyaTJ2cDB0dnYwamVwOXdv\\r\\ndHl0NGt4Nm9tNjd1bnU3MHQxMXlpZm5qMW42ZnIzc21nOWhmZnFpMGNyZHkyYXh0d2tkdmcwNnV3\\r\\ndXVlbXkxbDZwaDJueWNzZ2RvbmtqZTFkZ255MXFvY3g3bm5veGJ2cG5oNG5kdHE5bzBhenlsNjM0\\r\\naTBqdjl1MjZmbHFkZzlvdGZoN211MGo5a2xkMGdvOTdydzB1cGZ6am9uaHZoc2g3bXhpOG5weTR5\\r\\nejNyZnhvZXZwNmVvd3R0YmsxN3JrZjlpdGtwOWF5ZTVmaGV2czRtdm10MzE5dm01cTN1ZzF1NWlx\\r\\naXFicnFoNjZnN203NGxtZnk2b2l6YzhsYTc0ZWkzNHk5N2psYnNsdXN6ZW54dHFkMHR1NWF0dDU3\\r\\nNHZ5bmNlZGNhNjdlbzJxbnBvcGYyM3dvbDI5Y3VlaHRmeXpqOWM1dWJkMnp3MXo1dWJxNTE1eGFo\\r\\nemZqZXloeHowNndkeGwxajRqZW5oMHlrMnJ1dnlmZnNkdjE2a2JzOWVkMjlpZ3Z1NzRkM3NvZG02\\r\\nYjF6bjkxcHg0N21qeGd1cDg0ZGt2OW5ieTJqaTBmaDdnZWk3Y3R2OGhzOW1pc2g3dTNhbmx2bWk1\\r\\nNmh6eXZ2OW82YjVxM3ZldzB4ejBqcGpuZDRyczRlYWhycTN4ODAxcWVzYTdmNWhxZDB5MXU0eWxx\\r\\nMXprM3B4M2lmaHJsYmhldm8yem5obHRmYzQ3aXA0Z2w4MHJnMGNoMmwxbXh0dzMzZW1jNWd0bGQ5\\r\\nNHNlZnJnbmJ1dDh3MWZzYjZhb2E4cHV5aXAxbTF0dWEwY2o0d3BmM3Q2eGN3cXJ6YnBnbGs5Y3pp\\r\\nbnlzMXE1dGNyd2drY244MjJseGV6ZHk0ZzEyeXZwMmg3NWpwZzJqMnEzcmE2dnFzanJla2s4Z21y\\r\\naHFmeWJ1ZWNydXBzOHZ6ZzkxaXRuaXZzaWl5dXYxYzNlNHR6cnBzZHRuejdxdnlyZ2RkcjRuM3Zx\\r\\ndGQzZW83NWVqeTR0YjgwcDViY21udW5jeDdxODBuOG14ajVyNWY5ODFreXI5eXJuZG0zeHVvajZx\\r\\nbWNjaWV6MGMydmc1M2NnY2k2ZDZ0emVmZmJqcnM0eWk0OTU0cnQ5aXVtY2t4eGlsNHNjNmw2dGZr\\r\\neGM1MHZ1OG1uZHlhN3NyMHp1NnN4d2o2YzZreTVqaHZncGhkYTRyczJmMXVtYnFuY284YzNic3Nr\\r\\nbWM4d2c0a2FpZWs2bmJvdnR0cTR0eGloY2NwNmNwYnRxMTlqdXNtM2g0OGZxMTRvNm9ibGh5aWc3\\r\\nenZoMjRwMmljbmp3cjE1OTd5NXBqaXdnOXV5ZTRxbzl2NWVxejd5bGRkY3N1ZmE3Z3pxaXpoNHZ0\\r\\ndmo1OTA5eDVhdjZoc3d1am51bWwxZzJ5bXJpMmoxazRoZjd2aXpsZDN5b3c3bjNxeGU5OWZ0bW53\\r\\ncXNpcno2em4wMzB2dGM5Z3UyenltMDdqeHRpZXExZWhrc25jemluenVieXhlbWRwdnBxaDF6Z2k3\\r\\ndHN4eDNlcjNrMXBvN3Znb3psd2plMWN4N3Jnc2diZWhzZDE3OHdsbW4waTNlNjVrejk3ang1OGt5\\r\\nd3VleTNyYTQyZXQxcWtucG5jMzV3NDNmdXRhbmJpMnNjYXl4b3p4OXczMWF5b29iNmh1cjZ3YWYx\\r\\naTdjN3c1cjFhenR3MWluZmwza3E0YW94dDJ2MWRsZ2d5aWRqYWUxMGRocjl2d3ZlcDBlOXZndWNo\\r\\nOGg2M21oMnNka3hzY2J0YjNrN3VhdnprNnp2bWpnenhia3JyYnMwZXkzNWJxdm9qZmIxMmNxb2Yy\\r\\nYTZvOHBpNDQ4ZTJxMnl0MngweGVpeHNpOHVvbmR5a2twZ2ozOG96cDFna2duOGxteXA1N3p5Zm94\\r\\naG52c2w3NzUydHYwYjRkenYxcHkwcW4wend3a3Ayc3htcGNzb2w3eWt3NGlqanpiZ2JyODNsaG9m\\r\\nb3YzZGM4bTM1MWhleXd1b2JpOTlzNWVxMWxjZGZ4aDh6cWI3enVhOWJybnU4dTZ3aXQ0cTl2c2pv\\r\\na2ttcjI3NHJvaThqN2J0ZTNuOGE1eW9tdnhjY3drcGFncms2b2ttY2J5OGlpdjRpOXhyNG9xdzgz\\r\\nb2Zic2Q0NW9pd3EwaGE3NGI0cHd1MmI2M2tnZXd1MnA4ZWY4dHlrb2lrcTdhcXM5OHJ1ODhwZzIw\\r\\nMWM0NnFsMDloc3VyYm9xOHhnM2JyN3ZvN2NudGoyODlqbTlydmpzcXBucnZlaTUwaG9jbHd4NWV4\\r\\ndXFzbzZtcDN6YWdiZTBtNDA1MTdtZmdzb2l2YnIwMzZpdmFpeXpnOGdwOG5sZG9wZGR3dThqdWV5\\r\\nbjc2NHp0cnB4NnllNmkzN3BkbGF4MnJvaXVrNXpyOGJqZnlkc3EyY2UwdWhmbWR6MGxjand6eTc4\\r\\nNnJwMjNodGN2NHN0anExbGd3OGtjbGF5OWVkbW4xa3hjdHplcW4yZXBzejJyaTZkbjY1ZzFxNzh0\\r\\nZGxmY215bWd6ejhnNDZrN2F2ZmtwMTh0dTc4YjRlOXl5NnVlbXI0MjA5dTdhcGF5ZDA0OTV0c2lj\\r\\neDJlN281dHlodWFuZG91bzAzOWYydWk0YXlrbWJ5aGt6OHY2cnlxYWk0aHJycGMxeXluaTduNDR3\\r\\nbDJhY3o1dnlvZ215bDgwd2l4ejBnYWd0a2k0NnI3NWJvbnM5YnhpcWdkZWJxZmQ1b3RidjQ5Y214\\r\\nZGNlOXRweXVpYzRtd3hxdWs3c3I5czNhdWo3dXNsZ3VldjRocWY4aWR4ZDljNjVxeHJkZ2YzMnZx\\r\\nZjZrY2ZveTFyOGJlcWhvczVjM3c3YmI2dWQ0cjA0dnBhbnQwZ3FteDdheG5ramkzMXdtcDEyNTB1\\r\\ndjRkaW1ybGsxNWg4aWE1bXhhb3F1aXh3aHByaWk2dnd3ZmpkbXB3bTlwYnRvamQwOG93d2x4eXdp\\r\\naDU5MDh1cDJ1NzdjcTB0cm45ZnRkd2RvbzhvdTZxam8zMW0yY3pjbjduM211MWEwc2hiazhpbXhv\\r\\nNjhjMHgzYnViYTN5eDBrZzF1bWthZmRiZnJsYnN2OXN3Z3kwMzNzM2NsNTZjZDJlaGMyNG54a2N0\\r\\nejQ4aWNpenRrZWoxY2FlZnVjeHRtamVwbmFsYnpkamU3a3M5OWQya2txYnB4Z25qYWVmNzNweDI2\\r\\nYzhsdGpod25zNjhiYm0xend6ejdlajBubzR4YWM3dGhsMXJmNWJ6b2U3bGhxbnMxcmt1azNrZzY0\\r\\nM2k2NW90dWVvMGtsdTllN21xMmN5ZG8ycGNnOGx5aTQ1b2hlcmpuOG9xMDFkMmUxeTQxczVhMHJn\\r\\ndjI0N21jeTgyanJ1cDhsbG5oYmYxaHZyMGE2dW91b3YzYXI0dDM0Z3Jod2V3aWhkcW1xc2xud3p2\\r\\nbngyejJkYno3bjFndGs1dG81cjdob2RoczUwYTNxMXFtZ2o5MHZ2ZWN0OTlsaXJsMDEyb29hM3E1\\r\\ndGhhbXFicXZtdHppN2wxZHp5YTE0dmtkd3lmd3czcWcyNWlua2Jsa2p0c2Vkb3RhZWUyczcyZnJk\\r\\nYzE0ZG9kN3ZmcHp0cWY0N293NGRiMjdobTIyenk1cTN2ZzF6eWlheHhzNm51a2FzMHQydzR1bXFl\\r\\nc3p5NmNhZWc0emEzOGh5emh2cnBhNDgwMXlubWdlZ2N5OHhrZG40c3JkZWZxd3JnemZqaGo5ZTJy\\r\\nZnRkajN5MWFydGppMWlmbnlvNmtwam9hM2p4NTM5NDRjeGN5b2xwNDV1eWF3anZzdWplYng1emx5\\r\\nN2hjcGRoaWp0d2dwd2QzeWFraDRreGhraXVoYW5uMjdmeHZpOWI1cnlkc2dwMXk0aWR3ZWp3cmN0\\r\\nZzNlZ2xjem51ZHVzem81MjJoem10M296NXY2b2ZtdHdnZDJ1NXNuYWJqMzE1dmZreXk3dTJ3YzR6\\r\\ndGxuNnN3OWlrZ3JzYXE3MnFmNzh3Mml6aDRyaGhraHNjZTQ2YTdtMjd3bGl6azRycG90ZWh4cnFs\\r\\nOGYzN2todXg5c3dlbnN1NWtpN3B3eHpscDE2NzA5dnduaDA0N3N5ZTVubmFsMzQxcDlpMHNwcWJm\\r\\nbXk0OGJueWR5OW9pZ2ljbGNwMXk3NjQ2b2MzOXU1Y3YydmRhazg2Z2poaHR0a281YXpkY2R3enNl\\r\\najRvaTR6NWtnNGY0N3c1eHdvb2NkZng2cGNlcjBseHAzZTA2M3NkZnNybXd5dTQzd2xoMXBieG13\\r\\nenYyaGt2dnpkeHYwbWRzcXFoejFzaW4zZXY0eWZycjd6enp5Zjd6dmFqNTNtMXhiaHE0MXc0ZXdv\\r\\nZ3FlMWJycmt1N2w5Y3R0NmE5YmQydnFiem9tYXQ3dTM2dWhxaWZjZGxkN3J6cDJnc2t3OHlicmZ5\\r\\nMjhidTZ0NXpiNXI4bHEzZTEyb3d3ZzNmYjMybHNoODhrM3NzMWxwaGVvdTFiemhpbW10cTFnZWxk\\r\\nZjN1ZjdqdWszcXdlNTYwZHBzYXcxY2lhaW53ZDA0Zjh5YW0wN2JjdDNoc3BwMWwwYXYwMTVxcHQy\\r\\nNm5nNXI4dmlsd3ZoMWd5cGVmc2IyYTh4d2IyYmk3djFrMXEzd3J4Y21qY2YxamVueG5zMTJodHh4\\r\\nbnFwbjNtYnNiNW9yMDRvNDIxZW9zOGxuOXBvYXplOGsybGdtdHFjdTN6eWFkbXV6aWI1bTR1a2xw\\r\\ndm9pMDI1eWgzbzBwdHJyeHZlN3l5bnJicWZjZmtiNTFjd29mcW8yY3pidHF0dm5uNGhiYngzbHFk\\r\\ndDIwdGZ5dWV1M2FxODRybjQ4cg==";
            datamap.put("file_content", fileContent);
//            datamap.put("file_md5", "77ed36f4b18679ce54d4cebda306117e");
//            datamap.put("file_content",     f.getImageBinary("e:/2.jpg")); //64	文件内容（现将图片转化成二进制，再进行base64加密）
//            datamap.put("file_md5", f.getMD5(datamap.get("file_content")));
            datamap.put("file_md5", f.getMD5(datamap.get("file_content")));
            datamap.put("file_ext", imageType);
            datamap.put("storage", storage);


            /**
             * 1 data字段内容进行AES加密，再二进制转十六进制(bin2hex)
             */
            String data = TLinx2Util.handleEncrypt(datamap);
            postmap.put("data", data);
            /**
             * 2 请求参数签名 按A~z排序，串联成字符串，先进行sha1加密(小写)，再进行RSA加密(小写),二进制转十六进制，得到签名
             */
            String sign = TLinx2Util.handleSign(getmap, postmap);
            postmap.put("sign", sign);
            /**
             * 3 请求、响应
             */
            String url = StaticConfig.upload_url + StaticConfig.ORG1 + "?open_id=" + getmap.get("open_id") + "&lang=" + getmap.get("lang") + "&randStr=" + getmap.get("randStr")+  "&timestamp=" + getmap.get("timestamp");
            String rspStr = TLinx2Util.handlePost(url, postmap);

            /**
             * 4 验签  有data节点时才验签
             */
             respObject = JSONObject.fromObject(rspStr);

         
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return respObject;
    }
    public void download() {
        String timestamp = new Date().getTime() / 1000 + "";    // 时间
        try {
            // 固定参数
            TreeMap<String, String> postmap = new TreeMap<String, String>();    // post请求参数的map
            TreeMap<String, String> getmap  = new TreeMap<String, String>();    // get请求参数的map
            getmap.put("open_id", StaticConfig.open_id);
            getmap.put("lang", StaticConfig.lang);
            getmap.put("timestamp", timestamp);
            getmap.put("randStr", RandomStringUtils.randomAlphanumeric(32));

            TreeMap<String, String> datamap = new TreeMap<String, String>();    // data参数的map

            FileConver f = new FileConver();

            datamap.put("file_path", "201705/bfa3615e9caa7d508fefea1ec6f2edee.jpg");
            datamap.put("storage", "0");


            /**
             * 1 data字段内容进行AES加密，再二进制转十六进制(bin2hex)
             */
            String data = TLinx2Util.handleEncrypt(datamap);
            postmap.put("data", data);
            /**
             * 2 请求参数签名 按A~z排序，串联成字符串，先进行sha1加密(小写)，再进行RSA加密(小写),二进制转十六进制，得到签名
             */
            String sign = TLinx2Util.handleSign(getmap, postmap);
            postmap.put("sign", sign);
            /**
             * 3 请求、响应
             */
            String url = StaticConfig.upload_url + StaticConfig.DOWNLOAD + "?open_id=" + getmap.get("open_id") + "&lang=" + getmap.get("lang") + "&randStr=" + getmap.get("randStr")+  "&timestamp=" + getmap.get("timestamp");
            String rspStr = TLinx2Util.handlePost(url, postmap);

            /**
             * 4 验签  有data节点时才验签
             */
            JSONObject respObject = JSONObject.fromObject(rspStr);

            System.out.println("====响应错误码：" + respObject.get("errcode"));
            System.out.println("====响应错误提示：" + respObject.get("msg"));

            Object dataStr = respObject.get("data");

            if (!rspStr.isEmpty() || ( dataStr != null )) {
                if (TLinx2Util.verifySign(respObject, StaticConfig.publickey)) {    // 验签成功

                    /**
                     * 5 AES解密，并hex2bin
                     */
                    String respData = TLinxAESCoder.decrypt(dataStr.toString(), StaticConfig.open_key);
                    f.base64StringToImage(JSONObject.fromObject(respData).get("content").toString(), "E:/", "download.jpg");
                    System.out.println("==================响应data内容:" + respData);
                } else {
                    System.out.println("=====验签失败=====");
                }
            } else {
                System.out.println("=====没有返回data数据=====");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
