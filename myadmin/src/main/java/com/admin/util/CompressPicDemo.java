package com.admin.util;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
//此包是针对文件的上传时引用的包(springMVC)
import org.springframework.web.multipart.MultipartFile;
//下面这两个包已经被JDK1.7移除，在引用的时候要是报错的话
//在Eclipse中将编译器针对过时的类报错的地方改成警告即可
//（Preferences/Java/compiler/errors/warning/forbidden-->waring）
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
public class CompressPicDemo {

}
