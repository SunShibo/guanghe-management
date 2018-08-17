<%@ page language="java" pageEncoding="UTF-8"%>   
<%@page  
    import="java.util.*,java.io.*,   
    java.text.SimpleDateFormat,org.apache.commons.fileupload.*, org.apache.commons.fileupload.disk.*,
    org.apache.commons.fileupload.servlet.* ,
    net.sf.json.JSONObject,com.guanghe.management.pop.SystemConfig"%>
<%@ page import="org.springframework.web.context.request.RequestContextHolder" %>
<%@ page import="org.springframework.web.context.request.ServletWebRequest" %>
<%@ page import="com.aliyun.oss.OSSClient" %>
<%@ page import="com.aliyun.oss.model.ObjectMetadata" %>

<%

	final String endpoint = "http://oss-cn-beijing.aliyuncs.com";
	final String accessKeyId = "LTAIuK572qfOFVi8";
	final String accessKeySecret = "T52KlqdbHHCq344ohlHXPVYjMDUkRL";
			PrintWriter outPrint = response.getWriter();
        	//文件保存目录路径
			String savePath = SystemConfig.getString("image_bucketName") + "/" + SystemConfig.getString("img_file_root");

			// 设置访问路径
        	String saveUrl = "https://" +SystemConfig.getString("image_bucketName")+".oss-cn-beijing.aliyuncs.com/"+SystemConfig.getString("img_file_root")+"/";

			//定义允许上传的文件扩展名
			HashMap<String, String> extMap = new HashMap<String, String>();
			extMap.put("image", "gif,jpg,jpeg,png,bmp");

			//最大文件大小
			long maxSize = 3000000000l;
			
			response.setContentType("text/html; charset=UTF-8");
			
			if(!ServletFileUpload.isMultipartContent(request)){
				outPrint.print(getError("请选择文件。"));
				return;
			}

			
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");
			List items = upload.parseRequest(request);
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				String fileName = item.getName();
				long fileSize = item.getSize();
				if (!item.isFormField()) {
					//检查文件大小
					if(item.getSize() > maxSize){
						outPrint.print(getError("上传文件大小超过限制。"));
						return;
					}
					//检查扩展名
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();


					SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
					String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
					try{
//						File uploadedFile = new File(savePath, newFileName);
//						System.out.println(savePath);
//						item.write(uploadedFile);

						// 创建OSSClient实例
						OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
						try {
							// 创建上传Object的Metadata
							ObjectMetadata meta = new ObjectMetadata();
							meta.setContentType(getContentType(newFileName));
							ossClient.putObject(SystemConfig.getString("image_bucketName"),  "image/"+newFileName, item.getInputStream(), meta);
						} catch (Exception e) {
							e.printStackTrace();
						}
						// 关闭client
						ossClient.shutdown();
					}catch(Exception e){
						System.out.print(getError("上传文件失败。"));
						return;
					}
					JSONObject obj = new JSONObject();
					obj.put("error", 0);
					obj.put("url", saveUrl + newFileName);
					outPrint.print(obj.toString());
				}
			}
		%>
<%!
			private String getError(String message) {
				JSONObject obj = new JSONObject();
				obj.put("error", 1);
				obj.put("message", message);
				return obj.toString();
			}


	public static String getContentType(String fileName){
		String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
		System.out.println(fileExtension);
		if("bmp".equalsIgnoreCase(fileExtension)) {
			return "image/bmp";
		}else if("gif".equalsIgnoreCase(fileExtension)) {
			return "image/gif";
		}else if("jpeg".equalsIgnoreCase(fileExtension) || "jpg".equalsIgnoreCase(fileExtension)) {
			return "image/jpeg";
		}else if("png".equalsIgnoreCase(fileExtension)){
			return "image/png";
		}else if("html".equalsIgnoreCase(fileExtension)) {
			return "text/html";
		}else if("txt".equalsIgnoreCase(fileExtension)) {
			return "text/plain";
		}else if("vsd".equalsIgnoreCase(fileExtension)) {
			return "application/vnd.visio";
		}else if("ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension)) {
			return "application/vnd.ms-powerpoint";
		}else if("doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension)) {
			return "application/msword";
		}else if("xml".equalsIgnoreCase(fileExtension)) {
			return "text/xml";
		}else if("pdf".equalsIgnoreCase(fileExtension)){
			return "application/pdf";
		}else if("xls".equalsIgnoreCase(fileExtension) || "xlsx".equalsIgnoreCase(fileExtension)){
			return "application/vnd.ms-excel";
		}
		return "text/html";
	}
%>

