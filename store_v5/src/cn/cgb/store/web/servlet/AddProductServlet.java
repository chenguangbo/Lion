package cn.cgb.store.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import cn.cgb.store.domain.Product;
import cn.cgb.store.service.ProductService;
import cn.cgb.store.service.serviceImp.ProductServiceImp;
import cn.cgb.store.utils.UUIDUtils;
import cn.cgb.store.utils.UploadUtils;

public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//采用Product对象携带上传到服务端的表单数据
			Product product=new Product();
			//携带普通项中的键值对的数据
			Map<String,String> map=new HashMap<String,String>();
			
			//三行代码获取到集合:底层就是通过request获取到传递到服务端的所有数据,对分割线中中间内容进行封装
			DiskFileItemFactory fac=new DiskFileItemFactory();
			ServletFileUpload upload=new ServletFileUpload(fac);
			List<FileItem> list=upload.parseRequest(request);
			//遍历集合
			for(FileItem item:list){
				if(item.isFormField()){
					//普通项
					map.put(item.getFieldName(), item.getString("utf-8"));
				}else{
					//上传项
					//根据原始文件名称创建一个随机文件名称   111.bmp ===>2341242143.bmp
					String oldFileName=item.getName();
					//2341242143.bmp
					String finalFileName=UploadUtils.getUUIDName(oldFileName);
					
					//获取到准备上传到服务端目录真实路径 /products/3
					String realPath=getServletContext().getRealPath("/products/3");
					String randPath=UploadUtils.getDir(finalFileName);
					//E:\tomcat\webapps\store_v5\products\3/9/7/c/1/8/7/2/f
					System.out.println(realPath+randPath);
					//创建随机路径
					File dir=new File(realPath+randPath);
					//创建生成随机目录
					if(!dir.exists()){
						dir.mkdirs();
					}
					//创建文件在随机生成目录下
					File file=new File(dir,finalFileName);
					//创建文件
					if(!file.exists()){
						file.createNewFile();
					}
					
					OutputStream os=new FileOutputStream(file);
					InputStream is=item.getInputStream();
					IOUtils.copy(is,os);
					IOUtils.closeQuietly(is);
					IOUtils.closeQuietly(os);
					
					product.setPimage("products/3"+randPath+"/"+finalFileName);
					
				}
			}
			
			BeanUtils.populate(product, map);
			//为product对象的其他数据赋予值
			product.setPid(UUIDUtils.getId());
			product.setPdate(new Date());
			
			System.out.println(product);
			//Product [pid=null, pname=11, market_price=11.0, shop_price=11.0, pimage=products/3/a/d/5/3/2/f/6/0, pdate=null, is_hot=1, pdesc=111111, pflag=0, cid=1]
			
			ProductService ProductService=new ProductServiceImp();
			ProductService.saveProduct(product);
			
			response.sendRedirect(request.getContextPath()+"/admin/product/list.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}