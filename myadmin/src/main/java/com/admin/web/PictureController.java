package com.admin.web;

import com.admin.dto.Results;
import com.admin.entity.PicSuffix;
import com.admin.entity.User;
import com.admin.util.FileCommonUtil;
import com.admin.util.FileUploadUtils;
import com.admin.util.ThumbnailatorUtilss;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/8/12.
 */
@Controller
@RequestMapping("/picture")
public class PictureController extends  ApiController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @RequestMapping("picturelist")
    public String  pictureList(HttpServletRequest request) {
        //LOG.info("invoke----------/user/list"+user.getUserName());
        //  boolean flag = userService.addUser(user);
        //model.addAttribute("userlist", list);
        return "picture-list";
    }

    @RequestMapping("pictureadd")
    public String  pictureAdd(HttpServletRequest request) {
        //LOG.info("invoke----------/user/list"+user.getUserName());
        //  boolean flag = userService.addUser(user);
        //model.addAttribute("userlist", list);
        return "picture-add";
    }


    @RequestMapping("uploadshow")
    public String  upload(HttpServletRequest request) {
        //LOG.info("invoke----------/user/list"+user.getUserName());
        //  boolean flag = userService.addUser(user);
        //model.addAttribute("userlist", list);
        return "upload";
    }


    private static final String uploadFilePath = "d:\\temp_upload_file\\";

    @RequestMapping(value = "/pictureaddaction", method = RequestMethod.POST)
    public  void pictureAddAction(User user, HttpServletRequest request, HttpServletResponse response)
    {
        System.out.print(user.getUserName()+"--------");
        String picUrl="sasaas";
           /*
        try {

            MultipartFile uploadFile = user.getStudentPhoto();
            String filename = uploadFile.getOriginalFilename();
            InputStream is = uploadFile.getInputStream();
            // 如果服务器已经存在和上传文件同名的文件，则输出提示信息
            picUrl=filename;
            File folder = new File(uploadFilePath);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            File tempFile = new File(uploadFilePath + filename);
            if (tempFile.exists()) {
                boolean delResult = tempFile.delete();
                System.out.println("删除已存在的文件：" + delResult);
            }
            // 开始保存文件到服务器
            if (!filename.equals("")) {
                FileOutputStream fos = new FileOutputStream(uploadFilePath + filename);
                byte[] buffer = new byte[8192]; // 每次读8K字节
                int count = 0;
                // 开始读取上传文件的字节，并将其输出到服务端的上传文件输出流中
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count); // 向服务端文件写入字节流
                }
                fos.close(); // 关闭FileOutputStream对象
                is.close(); // InputStream对象
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        */

        user.setPic(picUrl);
        user.setScore(1);
       // user.setCreateTime(new Date());
        //userService.addUser(user);
        writeJson(response,"1");
    }



    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/upload", method = { RequestMethod.POST,RequestMethod.GET })
    public void upload(HttpServletRequest request, HttpServletResponse response) throws Exception{
        try {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);

            if (isMultipart) {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);

                // 得到所有的表单域，它们目前都被当作FileItem
                List<FileItem> fileItems = upload.parseRequest(request);

                String id = "";
                String fileName = "";
                // 如果大于1说明是分片处理
                int chunks = 1;
                int chunk = 0;
                FileItem tempFileItem = null;

                for (FileItem fileItem : fileItems) {
                    if (fileItem.getFieldName().equals("id")) {
                        id = fileItem.getString();
                    } else if (fileItem.getFieldName().equals("name")) {
                        fileName = new String(fileItem.getString().getBytes("ISO-8859-1"), "UTF-8");
                    } else if (fileItem.getFieldName().equals("chunks")) {
                        chunks = NumberUtils.toInt(fileItem.getString());
                    } else if (fileItem.getFieldName().equals("chunk")) {
                        chunk = NumberUtils.toInt(fileItem.getString());
                    } else if (fileItem.getFieldName().equals("file")) {
                        tempFileItem = fileItem;
                    }
                }

                // 临时目录用来存放所有分片文件
                String tempFileDir = getTempFilePath()	+ File.separator + id;
                File parentFileDir = new File(tempFileDir);
                if (!parentFileDir.exists()) {
                    parentFileDir.mkdirs();
                }
                // 分片处理时，前台会多次调用上传接口，每次都会上传文件的一部分到后台(默认每片为5M)
                File tempPartFile = new File(parentFileDir, fileName + "_" + chunk+ ".part");
                FileUtils.copyInputStreamToFile(tempFileItem.getInputStream(),
                        tempPartFile);

                // 是否全部上传完成
                // 所有分片都存在才说明整个文件上传完成
                boolean uploadDone = true;
                for (int i = 0; i < chunks; i++) {
                    File partFile = new File(parentFileDir, fileName + "_" + i
                            + ".part");
                    if (!partFile.exists()) {
                        uploadDone = false;
                    }
                }
                // 所有分片文件都上传完成
                // 将所有分片文件合并到一个文件中
                if (uploadDone) {
                    File destTempFile = new File(getTempFilePath(), fileName);
                    for (int i = 0; i < chunks; i++) {
                        File partFile = new File(parentFileDir, fileName + "_"
                                + i + ".part");

                        FileOutputStream destTempfos = new FileOutputStream(destTempFile, true);

                        FileUtils.copyFile(partFile, destTempfos);

                        destTempfos.close();
                    }
                    // 得到 destTempFile 就是最终的文件
                    // 添加到文件系统或者存储中

                    // 删除临时目录中的分片文件
                    //FileUtils.deleteDirectory(parentFileDir);
                    // 删除临时文件
                    //destTempFile.delete();
                } else {
                    // 临时文件创建失败
                    if (chunk == chunks -1) {
                        FileUtils.deleteDirectory(parentFileDir);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getTempFilePath() {
        // TODO Auto-generated method stub
        return "d:\\filetemp";
    }


    /**
     * 用户作品展示/企业风采上传
     * @param files 上传的文件
     * @param destDir 目标文件夹
     * @param request 请求
     */
    @RequestMapping(value = "/uploads", method = { RequestMethod.POST,RequestMethod.GET })
    public void uploads(MultipartFile[] files, String destDir,
                        HttpServletRequest request,HttpServletResponse response) throws Exception {
      //  User user = (User)request.getSession().getAttribute("user");
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
        // 获取文件上传的真实路径
        String uploadPath = request.getSession().getServletContext().getRealPath("/");
        String[] fileNames=null;
        try {
            fileNames = new String[files.length];
            int index = 0;
            //上传文件过程
            for (MultipartFile file : files) {
                String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
             //   int length = getAllowSuffix().indexOf(suffix);
             //   if (length == -1) {
                   // throw new Exception("请上传允许格式的文件");
                //}
                destDir = "staticResource/user/picture/asas";
                File destFile = new File(uploadPath + destDir);
                if (!destFile.exists()) {
                    destFile.mkdirs();
                }
                String fileNameNew = getFileNameNew() + "." + suffix;//
                File f = new File(destFile.getAbsoluteFile() + File.separator + fileNameNew);
                //如果当前文件已经存在了，就跳过。
                if(f.exists()){
                    continue;
                }
                file.transferTo(f);
                f.createNewFile();
                fileNames[index++] = basePath + destDir + fileNameNew;
            }


        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 为文件重新命名，命名规则为当前系统时间毫秒数
     * @return string
     */
    private String getFileNameNew() {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return fmt.format(new Date());
    }


    @RequestMapping(method = {RequestMethod.POST}, value = {"/webUploader"})
    public void webUploader(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (isMultipart) {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                // 得到所有的表单域，它们目前都被当作FileItem
                List<FileItem> fileItems = upload.parseRequest(request);
                String id = "";
                String fileName = "";
                // 如果大于1说明是分片处理
                int chunks = 1;
                int chunk = 0;
                FileItem tempFileItem = null;
                for (FileItem fileItem : fileItems) {
                    if (fileItem.getFieldName().equals("id")) {
                        id = fileItem.getString();
                    } else if (fileItem.getFieldName().equals("name")) {
                        fileName = new String(fileItem.getString().getBytes("ISO-8859-1"), "UTF-8");
                    } else if (fileItem.getFieldName().equals("chunks")) {
                        chunks = NumberUtils.toInt(fileItem.getString());
                    } else if (fileItem.getFieldName().equals("chunk")) {
                        chunk = NumberUtils.toInt(fileItem.getString());
                    } else if (fileItem.getFieldName().equals("multiFile")) {
                        tempFileItem = fileItem;
                    }
                }
                //session中的参数设置获取是我自己的原因,文件名你们可以直接使用fileName,这个是原来的文件名
                String fileSysName = request.getSession().getAttribute("fileSysName").toString();
                String realname = fileSysName+fileName.substring(fileName.lastIndexOf("."));//转化后的文件名
                request.getSession().setAttribute("realname",realname);
              //  String filePath = Const.VIDEOPATHFILE + "sound/";//文件上传路径
                String filePath ="sound/";//文件上传路径
                // 临时目录用来存放所有分片文件
                String tempFileDir = filePath + id;
                File parentFileDir = new File(tempFileDir);
                if (!parentFileDir.exists()) {
                    parentFileDir.mkdirs();
                }
                // 分片处理时，前台会多次调用上传接口，每次都会上传文件的一部分到后台
                File tempPartFile = new File(parentFileDir, realname + "_" + chunk + ".part");
                FileUtils.copyInputStreamToFile(tempFileItem.getInputStream(), tempPartFile);
                // 是否全部上传完成
                // 所有分片都存在才说明整个文件上传完成
                boolean uploadDone = true;
                for (int i = 0; i < chunks; i++) {
                    File partFile = new File(parentFileDir, realname + "_" + i + ".part");
                    if (!partFile.exists()) {
                        uploadDone = false;
                    }
                }
                // 所有分片文件都上传完成
                // 将所有分片文件合并到一个文件中
                if (uploadDone) {
                    // 得到 destTempFile 就是最终的文件
                    File destTempFile = new File(filePath, realname);
                    for (int i = 0; i < chunks; i++) {
                        File partFile = new File(parentFileDir, realname + "_" + i + ".part");
                        FileOutputStream destTempfos = new FileOutputStream(destTempFile, true);
                        //遍历"所有分片文件"到"最终文件"中
                        FileUtils.copyFile(partFile, destTempfos);
                        destTempfos.close();
                    }
                    // 删除临时目录中的分片文件
                    FileUtils.deleteDirectory(parentFileDir);
                } else {
                    // 临时文件创建失败
                    if (chunk == chunks -1) {
                        FileUtils.deleteDirectory(parentFileDir);
                    }
                }
            }
        } catch (Exception e) {
          //  logger.error(e.getMessage(), e);
        }
    }

    // 文件存储的根目录,放在程序运行的发布目录中，一般是在tomcat的安装同目录下
    String rootPath = getTempFilePath();

    Map<String, Object> fileIdMap = new HashMap<String, Object>();
    /**
     * 参数定义 CommonsMultipartFile 接收文件上传内容
     * AjaxResult  表示传达的是ajax数据
     * @param //file是不能改变的，因为webuploader是以file参数往后台   传文件的；ff是自定义的文件实体
     * @return
     * @throws //IOException
     * @throws IllegalStateException
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, path = "upload/up")
    public void upload(@RequestParam(name = "file", required = false) CommonsMultipartFile file,HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException {
        //System.out.println("1212212！！");
        // 判断是否有文件
        if (file != null && !file.isEmpty()) {
            // 获取文件的原始名称
            String oldName = file.getOriginalFilename();
            // 获取文件大小
            Long fileSize = file.getSize();
            // 获取文件的原始流
            // f.getInputStream()
            // 获取文件的类型
            //System.out.println(file.getContentType());

            // 组装文件存储路径
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy" + File.separator + "MM" + File.separator + "dd");
            String dateStr = sdf.format(new Date());
            String filePath = rootPath+ File.separator + dateStr;

            // 创建目录
            File f = new File(filePath);
            if (!f.exists()) {
                f.mkdirs();
            }

            // 生成一个新的不会重复的文件名
            // 1.获取后缀
            String suffix = FilenameUtils.getExtension(file.getOriginalFilename());
            // 2.生成新的文件名
            String newFileName = UUID.randomUUID().toString() + "." + suffix;
            // 把上传的文件存储指定位置
            file.transferTo(new File(f, newFileName));
            String FilePath = rootPath  + File.separator + dateStr + File.separator + newFileName;

            /*
            ff.setFileModel(model);
            ff.setFileName(oldName);
            ff.setFilePath(FilePath.replace("\\", "/"));
            ff.setFileSize(fileSize);
            ff.setFileType(suffix);
            fileDao.save(ff);
            fileIdMap.put("fileId", ff.getFileId());
            */
        } else {
            System.out.println("上传失败！！");
        }
      //  writeJson(response,"1");

       // return new AjaxResult(fileIdMap);
    }

    // 文件下载,表示/upload后面接的任何路径都会进入到这里
    @RequestMapping("/**")
    public void view(HttpServletResponse response, HttpServletRequest request)
            throws Exception {
        String filePath = request.getServletPath().replaceFirst("/upload/", "");
        File file = new File(rootPath, filePath);

        /*
        if (file.exists()) {
            List<cn.gson.ams.entity.file.File> list = fileDao.findByFilePath(request.getServletPath());
            String fileName = list.get(0).getFileName();

            // 设置下载文件的名称,如果想直接在想查看就注释掉，因为要是文件原名才能下载，不然就只能在浏览器直接浏览无法下载
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

            // 把文件输出到浏览器
            OutputStream os = response.getOutputStream();
            FileInputStream fs = new FileInputStream(file);
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = fs.read(b)) > 0) {
                os.write(b, 0, len);
            }
            fs.close();
            os.flush();

        } else {
            response.sendError(404);
        }
        */
    }

    /**
     * 实现文件上传
     * @param fileUpload
     * @param request
     * @return
     */
    @RequestMapping( "/fileUpload" )
    public void fileUpload(@RequestParam ("file") MultipartFile fileUpload,HttpServletRequest request,HttpServletResponse response){

        SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMMddhhmmss" );
        String fileName = sFormat.format(Calendar.getInstance().getTime())+ new Random().nextInt(1000);
        String originalFilename = fileUpload.getOriginalFilename();
        fileName += originalFilename.substring(originalFilename.lastIndexOf("." ));
        String dirName = request.getSession().getServletContext().getRealPath("/" )+"fileUpload" ;

        double originalFilesize = request.getContentLength();//获取源文件大小

        File file = new File(dirName);
        InputStream inputStream = null ;
        FileOutputStream outputStream = null ;
        if (!file.exists())
        {
            file.mkdir();
        }
        try
        {
            inputStream = fileUpload.getInputStream();
            if (!inputStream.equals(null)){
                try {
                 //   String table_type = request.getParameter("table_type" );
                  //  DocManagement docManagement = new DocManagement();
                  //  docManagement.setFilename(originalFilename);
                   // docManagement.setFileurl( "/fileUpload/" +fileName);
                   // docManagement.setFilesize(originalFilesize);
                   /// docManagement.setTable_type(table_type);
                   // dManagementService .addDocFile(readCurrentOperator(),docManagement);//直接存入数据库表
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
            outputStream = new FileOutputStream(dirName+"/" +fileName);
            byte [] buffer = new byte[1024 * 1024];
            int len=0;
            while ((len=inputStream.read(buffer)) != -1)
            {
                outputStream.write(buffer, 0, len);
                outputStream.flush();
            }
            outputStream.close();
            inputStream.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    @RequestMapping("/batchupload" )
    public  Object  batchUpload(HttpServletRequest request,HttpServletResponse response)
    {
        JSONObject rejson = new JSONObject();
        try {
            System.out.print("----sddddddddddddddd");
            FileUploadUtils fileUp = new FileUploadUtils();
            rejson = fileUp.baiduUpload("routeUpload", request);
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject result = new JSONObject();
            result.put("msg", "系统异常");
            result.put("type", false);
            result.put("data", rejson);
            return result;
        }
        return rejson;
    }



    @RequestMapping("uploadPicture")
    public void uploadFile(@RequestParam("file") MultipartFile[] files,HttpServletRequest request,HttpServletResponse response) {
        try {
            uploadp(files,request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void uploadp(MultipartFile[] files,HttpServletRequest request,HttpServletResponse response) throws Exception {
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" +
                request.getServerPort() + path;
        // 获取文件上传的真实路径
        String uploadPath = request.getSession().getServletContext().getRealPath("/");
        List<String> picPaths = new ArrayList<String>();
        String[] fileNames=null;
        String destDir="";
        try {
            fileNames = new String[files.length];
            int index = 0;
            //上传文件过程
            for (MultipartFile file : files) {
                String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
                int length = new PicSuffix().getAllowSuffix().indexOf(suffix);
                if (length == -1) {
                    throw new Exception("请上传允许格式的文件");
                }
                destDir = "staticResource/user/picture";
                LOG.info(uploadPath+"---"+destDir);
                File destFile = new File(uploadPath + destDir);
                if (!destFile.exists()) {
                    destFile.mkdirs();
                }
                String fileNameNew = getFileNameNew() + "." + suffix;//
                File f = new File(destFile.getAbsoluteFile() + File.separator + fileNameNew);
                //如果当前文件已经存在了，就跳过。
                if(f.exists()){
                    continue;
                }
                file.transferTo(f);
                f.createNewFile();
                //fileNames[index++] = basePath + destDir + fileNameNew;
                fileNames[index++] = destDir + fileNameNew;
            }

            String str="";
            if(fileNames!=null && fileNames.length>0)
            {
                for (String  m:fileNames) {
                    //str=str+m+",";
                    str=m;
                }
            }
         //  String ret="{\"url\":\""+str+"\"}";
            LOG.info("---"+str);
            writeJson(response,str);

        } catch (Exception e) {
            throw e;
        }
    }


    @RequestMapping("uploadPic")
    @ResponseBody
    public Results uploadPic(@RequestParam("file") MultipartFile[] files, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<String>  uList=new ArrayList<String>();
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" +
                request.getServerPort() + path;
        // 获取文件上传的真实路径
       // FileCommonUtil.endFileDir(

        String uploadPath = request.getSession().getServletContext().getRealPath("/");
        List<String> picPaths = new ArrayList<String>();
        String[] fileNames=null;
        String destDir="";
        try {
            fileNames = new String[files.length];
            int index = 0;
            //上传文件过程
            for (MultipartFile file : files) {
                String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
                int length = new PicSuffix().getAllowSuffix().indexOf(suffix);
                if (length == -1) {
                    //throw new Exception("请上传允许格式的文件");
                    return Results.build(-001,"请上传允许格式的文件");
                }
                destDir = "staticResource/user/picture";
                LOG.info(uploadPath+"---"+destDir);
                File destFile = new File(uploadPath + destDir);
                if (!destFile.exists()) {
                    destFile.mkdirs();
                }
                String fileNameNew = getFileNameNew() + "." + suffix;//
                String filepath=destFile.getAbsoluteFile() + File.separator + fileNameNew;
                String thumbpath=destFile.getAbsoluteFile() + File.separator +"thumbpath"+ fileNameNew;
                File f = new File(destFile.getAbsoluteFile() + File.separator + fileNameNew);
                //如果当前文件已经存在了，就跳过。
                if(f.exists()){
                    continue;
                }
                file.transferTo(f);
                f.createNewFile();
                //fileNames[index++] = basePath + destDir + fileNameNew;

                ThumbnailatorUtilss.ImgThumb(filepath,thumbpath,100,100);
                fileNames[index++] = destDir + fileNameNew;
            }
            String str="";
            if(fileNames!=null && fileNames.length>0)
            {
                for (String  m:fileNames) {
                    //str=str+m+",";
                    uList.add(m);
                //    str=m;
                }
            }
            LOG.info("---"+str);
            return Results.build(0,"上传成功",uList);
        } catch (Exception e) {
            return Results.build(-001,"上传失败");
        }
    }



    /*
     public void Upload()
         {
                    string fileName = Request["name"];
                     int index = Convert.ToInt32(Request["chunk"]);//当前分块序号
                   var guid = Request["guid"];//前端传来的GUID号
                   var dir = Server.MapPath("~/Upload");//文件上传目录
             dir = Path.Combine(dir, guid);//临时保存分块的目录
                     if (!System.IO.Directory.Exists(dir))
                           System.IO.Directory.CreateDirectory(dir);
                   string filePath = Path.Combine(dir, index.ToString());//分块文件名为索引名，更严谨一些可以加上是否存在的判断，防止多线程时并发冲突
                    var data = Request.Files["file"];//表单中取得分块文件
                    if (data != null)//为null可能是暂停的那一瞬间
                       {
                           data.SaveAs(filePath);//报错
                       }
                     return Json(new { erron = 0 });//Demo，随便返回了个值，请勿参考
         }



    public ActionResult Merge()
         {
                    var guid = Request["guid"];//GUID
                     var uploadDir = Server.MapPath("~/Upload");//Upload 文件夹
                    var dir = Path.Combine(uploadDir, guid);//临时文件夹
                     var fileName = Request["fileName"];//文件名
                     var files = System.IO.Directory.GetFiles(dir);//获得下面的所有文件
                     var finalPath = Path.Combine(uploadDir, fileName);//最终的文件名（demo中保存的是它上传时候的文件名，实际操作肯定不能这样）
                     var fs = new FileStream(finalPath, FileMode.Create);
                    foreach (var part in files.OrderBy(x => x.Length).ThenBy(x => x))//排一下序，保证从0-N Write
                    {
                             var bytes = System.IO.File.ReadAllBytes(part);
                            fs.Write(bytes, 0, bytes.Length);
                             bytes = null;
                        System.IO.File.Delete(part);//删除分块
                        }
             fs.Close();
                     System.IO.Directory.Delete(dir);//删除文件夹
                    return Json(new { error = 0 });//随便返回个值，实际中根据需要返回
              }

              */

    /**
     * 为文件重新命名，命名规则为当前系统时间毫秒数
     * @return string
     */
    private String getFileNameNews() {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return fmt.format(new Date());
    }
    /*
    private String serverPath = "e:/";
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("进入合并后台...");
        String action = request.getParameter("action");
        if ("mergeChunks".equals(action)) {
            // 获得需要合并的目录
            String fileMd5 = request.getParameter("fileMd5");
            // 读取目录所有文件
            File f = new File(serverPath + "/" + fileMd5);
            File[] fileArray = f.listFiles(new FileFilter() {
                // 排除目录，只要文件
                @Override
                public boolean accept(File pathname) {
                    if (pathname.isDirectory()) {
                        return false;
                    }
                    return true;
                }
            });
            // 转成集合，便于排序
            List<File> fileList = new ArrayList<File>(Arrays.asList(fileArray));
            // 从小到大排序
            Collections.sort(fileList, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    if (Integer.parseInt(o1.getName()) < Integer.parseInt(o2.getName())) {
                        return -1;
                    }
                    return 1;
                }
            });
            // 新建保存文件
            File outputFile = new File(serverPath + "/" + UUID.randomUUID().toString() + ".zip");
            // 创建文件
            outputFile.createNewFile();
            // 输出流
            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
            FileChannel outChannel = fileOutputStream.getChannel();
            // 合并
            FileChannel inChannel;
            for (File file : fileList) {
                inChannel = new FileInputStream(file).getChannel();
                inChannel.transferTo(0, inChannel.size(), outChannel);
                inChannel.close();
                // 删除分片
                file.delete();
            }
            // 关闭流
            fileOutputStream.close();
            outChannel.close();
            // 清除文件加
            File tempFile = new File(serverPath + "/" + fileMd5);
            if (tempFile.isDirectory() && tempFile.exists()) {
                tempFile.delete();
            }
            System.out.println("合并文件成功");
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
*/


    public void fileUpload(HttpServletRequest request,
                           HttpServletResponse response) throws ServletException {
        try {
            String path = request.getParameter("path");
            path = path != null ? java.net.URLDecoder.decode(path, "utf-8")
                    : "";
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);

            if (isMultipart) {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);

                // 得到所有的表单域，它们目前都被当作FileItem
                List<FileItem> fileItems = upload.parseRequest(request);

                String id = "";
                String fileName = "";
                // 如果大于1说明是分片处理
                int chunks = 1;
                int chunk = 0;
                FileItem tempFileItem = null;

                for (FileItem fileItem : fileItems) {
                    if (fileItem.getFieldName().equals("id")) {
                        id = fileItem.getString();
                    } else if (fileItem.getFieldName().equals("name")) {
                        fileName = new String(fileItem.getString().getBytes(
                                "ISO-8859-1"), "UTF-8");
                    } else if (fileItem.getFieldName().equals("chunks")) {
                        chunks = NumberUtils.toInt(fileItem.getString());
                    } else if (fileItem.getFieldName().equals("chunk")) {
                        chunk = NumberUtils.toInt(fileItem.getString());
                    } else if (fileItem.getFieldName().equals("file")) {
                        tempFileItem = fileItem;
                    }
                }

                // 临时目录用来存放所有分片文件
                String tempFileDir = getTempFilePath()
                        + File.separator + id;
                File parentFileDir = new File(tempFileDir);
                if (!parentFileDir.exists()) {
                    parentFileDir.mkdirs();
                }
                // 分片处理时，前台会多次调用上传接口，每次都会上传文件的一部分到后台(默认每片为5M)
                File tempPartFile = new File(parentFileDir, fileName + "_" + chunk
                        + ".part");
                FileUtils.copyInputStreamToFile(tempFileItem.getInputStream(),
                        tempPartFile);

                // 是否全部上传完成
                // 所有分片都存在才说明整个文件上传完成
                boolean uploadDone = true;
                for (int i = 0; i < chunks; i++) {
                    File partFile = new File(parentFileDir, fileName + "_" + i
                            + ".part");
                    if (!partFile.exists()) {
                        uploadDone = false;
                    }
                }
                // 所有分片文件都上传完成
                // 将所有分片文件合并到一个文件中
                if (uploadDone) {
                    File destTempFile = new File(getTempFilePath(), fileName);
                    for (int i = 0; i < chunks; i++) {
                        File partFile = new File(parentFileDir, fileName + "_"
                                + i + ".part");

                        FileOutputStream destTempfos = new FileOutputStream(
                                destTempFile, true);

                        FileUtils.copyFile(partFile, destTempfos);

                        destTempfos.close();
                    }
                    // 得到 destTempFile 就是最终的文件
                    // 添加到文件系统或者存储中

                    // 删除临时目录中的分片文件
                    FileUtils.deleteDirectory(parentFileDir);
                    // 删除临时文件
                    destTempFile.delete();

                   // ResponseUtil.responseSuccess(response, null);
                } else {
                    // 临时文件创建失败
                    if (chunk == chunks -1) {
                        FileUtils.deleteDirectory(parentFileDir);
                      //  ResponseUtil.responseFail(response, "500", "内部错误");
                    }
                }
            }
        } catch (Exception e) {
          //  logger.error(e.getMessage(), e);
           // ResponseUtil.responseFail(response, "500", "内部错误");
        }
    }



    @RequestMapping("/blockupload" )
    public  void   blockUpload(HttpServletRequest request,HttpServletResponse response)
    {
        String json = request.getParameter("json");
        String video = request.getParameter("video");
       // System.out.println("json " + json);
        try {
            f_uploadVedio(json,video);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        writeJson(response,"1");
    }


    /**
     * 音视频图片处理
     *
     * @param mStr
     * @return
     * @throws Exception
     */
    public static String f_uploadVedio(String mStr,String data) {
        try {
            String mResult = "";
            String fileType = "video";
            int startPosL = 0;
            RandomAccessFile oSavedFile = null;
            JSONObject jsonObject = JSONObject.parseObject(mStr);
            //byte[] vedioBytes = jsonObject.getString("VEDIO").getBytes("UTF-8");
            byte[] vedioBytes = decode(data);
            for (int i = 0; i < vedioBytes.length; ++i) {
                if (vedioBytes[i] < 0) {
                    // 调整异常数据
                    vedioBytes[i] += 256;
                }
            }
            startPosL = (Integer) jsonObject.get("start"); // 接收客户端的开始位置(文件读取到的字节大小)
            fileType = (String) jsonObject.getString("filetype");
            String fileName = (String) jsonObject.getString("FileName");
            System.out.println("fileName " + fileName);
            File dir = new File("/home/pic/");
            dir.setReadable(true);//设置可读权限
            dir.setWritable(true);//设置可写权限
            if(!dir.exists()){
                dir.mkdirs();
            }
            if (fileType.equals("picture")) {
               //oSavedFile = new RandomAccessFile("D:\\" + fileName + ".jpg", "rw");
               oSavedFile = new RandomAccessFile("/home/pic/" + fileName + ".jpg", "rw");
            } else if (fileType.equals("photo")) {
               // oSavedFile = new RandomAccessFile("D:\\" + fileName + ".jpg", "rw");
                oSavedFile = new RandomAccessFile("/home/pic/" + fileName + ".jpg", "rw");
            } else if (fileType.equals("voice")) {
               // oSavedFile = new RandomAccessFile("D:\\" + fileName + ".mp3", "rw");
                oSavedFile = new RandomAccessFile("/home/pic/" + fileName + ".mp3", "rw");
            } else if (fileType.equals("video")) {
               // oSavedFile = new RandomAccessFile("D:\\" + fileName, "rw");
                oSavedFile = new RandomAccessFile("/home/pic/" + fileName, "rw");

            }
            // 设置标志位,标志文件存储的位置
            oSavedFile.seek(startPosL);
            oSavedFile.write(vedioBytes);
            oSavedFile.close();
            mResult = "000";
            return mResult;
        }catch (Exception e)
        {
            e.printStackTrace();
            return  null;
        }
    }

    public static byte[] decode(String str) {
        byte[] bt = null;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            bt = decoder.decodeBuffer(str);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bt;
    }

    public static String encode(byte[] bstr) {
        return new BASE64Encoder().encode(bstr);
    }


}
