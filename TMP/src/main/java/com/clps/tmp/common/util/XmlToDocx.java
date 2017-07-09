package com.clps.tmp.common.util;
//package com.clps.tmp.common.util;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStreamWriter;
//import java.nio.channels.FileChannel;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.Properties;
//import java.util.zip.ZipEntry;
//import java.util.zip.ZipInputStream;
//import java.util.zip.ZipOutputStream;
//
//import org.apache.velocity.Template;
//import org.apache.velocity.VelocityContext;
//import org.apache.velocity.app.Velocity;
//
///**
// * 通过word模板生成word文件的类
// * @author wangchao 
// * Create Date 2010-4-29
// */
//public class One {
// 
// private static String SPLITER = "/";
// private static String WORD = "word";
// private static String DOCUMENT = "document.xml";
// private static String ENCODING = "UTF-8";
// private static String LODERPATH = "file.resource.loader.path";
// private static String TEMPDIR = "/temp";
// private static String TEMPLATEFILENAME = "template.docx";
// 
// /**
//  * 使用word文件模板生成word文件
//  * @param dataMap 数据
//  * @param templatePath 模板文件路径
//  * @param outFilePath 目标文件
//  * @throws Exception
//  */
// public static synchronized void createWord(HashMap<String,Object> dataMap,String templatePath,String outFilePath) throws Exception{
//  if(templatePath!=null&&!templatePath.endsWith(SPLITER))
//  {
//   templatePath+=SPLITER;
//  }
//  //定义加入数据后的document.xml文件
//  File documentFile =  new File(injectData(dataMap,templatePath));
//  //定义目标文件
//  File targetFile = new File(outFilePath);
//  //定义模板文件
//  File templateFile = new File(templatePath+TEMPLATEFILENAME);
//  if(targetFile.exists()){
//   targetFile.delete();
//  }
//  //将模板文件拷贝一份作为目标文件
//  fileCopy(templateFile,targetFile);
//  //在目标文件中加入新的document.xml文件
//  addDocumentToTemplate(targetFile,documentFile);
//  //删除临时的document.xml文件
//  documentFile.delete();
// }
// 
// /**
//  * 想模板装入数据的方法
//  * @param dataMap 要装入的数据
//  * @param templatePath 模板路径
//  * @return 返回装入数据完成的xml文件路径
//  * @throws Exception
//  */
// private static String injectData(HashMap<String,Object> dataMap, String templatePath)throws Exception {
//  
//  //Init Velocity
//  Properties p = new Properties();
//  p.setProperty(LODERPATH, templatePath+WORD);
//  Velocity.init(p);
//  
//  VelocityContext context = new VelocityContext();
//  //Build Velocity Context
//  for (Iterator<Map.Entry<String,Object>> iter = dataMap.entrySet().iterator(); iter.hasNext();) {   
//      Map.Entry<String,Object> entry = (Map.Entry<String,Object>) iter.next();   
//      context.put(entry.getKey(), entry.getValue());
//  }
//  
//  Template template = null;
//  template = Velocity.getTemplate(DOCUMENT, ENCODING);
//  String templateDocPath = templatePath+WORD+TEMPDIR+SPLITER+DOCUMENT;
//  File tempdir = new File(templatePath+WORD+TEMPDIR+SPLITER);
//  tempdir.mkdirs();
//  OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(templateDocPath),ENCODING);
//  if (template != null)
//   template.merge(context, writer);
//  writer.flush();
//  writer.close();
//  return templateDocPath;
// }
//
// /**
//  * 将要document.xml文件的数据装入到模板中
//  * @param targetFile 目标模板文件
//  * @param document document.xml文件
//  * @throws IOException
//  */
// private static void addDocumentToTemplate(File targetFile, File document)throws IOException {
//  // get a temp file
//  File tempFile = File.createTempFile(targetFile.getName(), null);
//  // delete it, otherwise you cannot rename your existing zip to it.
//  tempFile.delete();
//
//  boolean renameOk = targetFile.renameTo(tempFile);
//  if (!renameOk) {
//   throw new RuntimeException("could not rename the file " + targetFile.getAbsolutePath() + " to "+ tempFile.getAbsolutePath());
//  }
//  byte[] buf = new byte[1024];
//
//  ZipInputStream zin = new ZipInputStream(new FileInputStream(tempFile));
//  ZipOutputStream out = new ZipOutputStream(new FileOutputStream(targetFile));
//
//  ZipEntry entry = zin.getNextEntry();
//  while (entry != null) {
//   String name = entry.getName();
//   boolean notInFiles = true;
//   if (document!=null) {
//    if (document.getName().equals(name)) {
//     notInFiles = false;
//     break;
//    }
//   }
//   if (notInFiles) {
//    // Add ZIP entry to output stream.
//    out.putNextEntry(new ZipEntry(name));
//    // Transfer bytes from the ZIP file to the output file
//    int len;
//    while ((len = zin.read(buf)) > 0) {
//     out.write(buf, 0, len);
//    }
//   }
//   entry = zin.getNextEntry();
//  }
//  // Close the streams
//  zin.close();
//  // Compress the files
//  if(document!=null) {
//   InputStream in = new FileInputStream(document);
//   // Add ZIP entry to output stream.
//   out.putNextEntry(new ZipEntry(WORD+SPLITER + System.currentTimeMillis()+document.getName()));
//   // Transfer bytes from the file to the ZIP file
//   int len;
//   while ((len = in.read(buf)) > 0) {
//    out.write(buf, 0, len);
//   }
//   // Complete the entry
//   out.closeEntry();
//   in.close();
//  }
//  // Complete the ZIP file
//  out.close();
////  tempFile.delete();
// }
// 
// /**
//  * 文件拷贝
//  * @param f1 拷贝源文件
//  * @param f2 拷贝目标文件
//  * @return
//  * @throws Exception
//  */
//    private static long fileCopy(File f1,File f2) throws Exception{
//         long time=new Date().getTime();
//         int length=2097152;
//         FileInputStream in=new FileInputStream(f1);
//         FileOutputStream out=new FileOutputStream(f2);
//         FileChannel inC=in.getChannel();
//         FileChannel outC=out.getChannel();
//         int i=0;
//         while(true){
//             if(inC.position()==inC.size()){
//                 inC.close();
//                 outC.close();
//                 return new Date().getTime()-time;
//             }
//             if((inC.size()-inC.position())<20971520)
//                 length=(int)(inC.size()-inC.position());
//             else
//                 length=20971520;
//             inC.transferTo(inC.position(),length,outC);
//             inC.position(inC.position()+length);
//             i++;
//         }
//     }
//}

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class XmlToDocx {  
  
    /** 
     *  
     * @param documentFile 动态生成数据的docunment.xml文件 
     * @param docxTemplate docx的模板 
     * @param toFileName 需要导出的文件路径 
     * @throws ZipException 
     * @throws IOException 
     */  
  
    public void outDocx(File documentFile, String docxTemplate, String toFilePath) throws ZipException, IOException {  
  
        try {  
            File docxFile = new File(docxTemplate);  
            ZipFile zipFile = new ZipFile(docxFile);  
            Enumeration<? extends ZipEntry> zipEntrys = zipFile.entries();  
            ZipOutputStream zipout = new ZipOutputStream(new FileOutputStream(toFilePath));  
            int len = -1;  
            byte[] buffer = new byte[1024];  
            while (zipEntrys.hasMoreElements()) {  
                ZipEntry next = zipEntrys.nextElement();  
                InputStream is = zipFile.getInputStream(next);  
                // 把输入流的文件传到输出流中 如果是word/document.xml由我们输入  
                zipout.putNextEntry(new ZipEntry(next.toString()));  
                if ("word/document.xml".equals(next.toString())) {  
                    InputStream in = new FileInputStream(documentFile);  
                    while ((len = in.read(buffer)) != -1) {  
                        zipout.write(buffer, 0, len);  
                    }  
                    in.close();  
                } else {  
                    while ((len = is.read(buffer)) != -1) {  
                        zipout.write(buffer, 0, len);  
                    }  
                    is.close();  
                }  
            }  
            zipout.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}  