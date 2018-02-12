package com.ddf.student.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ddf.entity.base.dto.Page;
import com.ddf.entity.student.dto.Student;
import com.ddf.entity.student.query.StudentQuery;
import com.ddf.entity.student.vo.StudentVo;
import com.ddf.student.service.simple.StudentService;


@RestController
public class StudentApi {

	@Autowired
	StudentService studentService;
	/*@Autowired
	private FastdfsApi fastdfsApi;
	@Autowired
	private FastdfsReadApi fastdfsReadApi;
	
	@Value("${server.port}")
	String port;
	
	@RequestMapping("/student/query")
    public Student query(@RequestParam String id) {
		String download_img = fastdfsApi.saveFile4httpUrl("https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=2392375712,1477006874&fm=173&s=72B339C5600A234718B05DAD03007002&w=640&h=466&img.JPEG");
		String url=fastdfsReadApi.getServerUrl(download_img);
		System.out.println(url);
        return studentService.queryStudent(id);
    }*/
	
	@RequestMapping("/student/query")
	public Student query(@RequestParam String id) {
		return studentService.query4id(id);
	}
	
	@RequestMapping("/student/create")
	public boolean create(Student student){
		return studentService.create(student);
	}
	
	@RequestMapping("/student/modify")
	public boolean modify(Student student){
		return studentService.modify(student);
	}
	
	@RequestMapping("/student/remove")
	public boolean remove(String id){
		return studentService.remove(id);
	}
}
