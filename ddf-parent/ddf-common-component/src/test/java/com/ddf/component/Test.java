package com.ddf.component;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.ArrayList;
import java.util.List;

import com.ddf.component.jdbc.StudentDao;
import com.ddf.component.jdbc.base.CrudDao;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ddf.util.ArrayUtil;
import com.ddf.component.jdbc.util.SqlBuildUtil;
import com.ddf.entity.base.dto.Page;
import com.ddf.entity.student.dto.Student;
import com.ddf.entity.student.vo.StudentVo;

public class Test {

	private static ClassPathXmlApplicationContext  context;
	private static StudentDao studentDao;
	static {
		context = new ClassPathXmlApplicationContext("classpath:/frame/dispatcher-servlet.xml");
		studentDao=context.getBean("studentDao", StudentDao.class);
	}
	public static ClassPathXmlApplicationContext getContext() {
		return context;
	}
	
	/*
	public static void testXmemcached_put(){
		memCacheApi.put("aa", "哈哈nihao");
	}
	
	public static void testXmemcached_get(){
		System.out.println(memCacheApi.get("aa", String.class));
	}
	
	public static void testHx_(){
		hxApi.getToken();
	}
	*/
	public static void main(String[] args){
		//System.out.println(studentDao.query4id("1").getName());
		
		//System.out.println(studentDao.execute("update student set age=100 where id='1'"));
		
		/*List<StudentVo> list = studentDao.queryList("SELECT * from student");
		for(StudentVo vo : list){
			System.out.println("id is " + vo.getId()+"; name is :"+vo.getName());
		}*/
		
		/*List<StudentVo> list = studentDao.queryList("SELECT s.*,sc.score from student s,stu_score sc where s.id = sc.student_id");
		for(StudentVo vo : list){
			System.out.println("id is " + vo.getId()+"; name is :"+vo.getName()+"; score is " + vo.getScore());
		}*/
		
		
		Page<StudentVo> page = studentDao.queryPage();
		System.out.println(page.getTotalCount());
		for(StudentVo vo : page.getList()){
			System.out.println("id is " + vo.getId()+"; name is :"+vo.getName());
		}
		
		
		/*Student stu = new Student();
		stu.setId(idReference.createId("Stu"));
		stu.setAge(11);
		stu.setName("hujuntest");
		
		studentDao.remove("Stu1711251700002");*/
		//Student stu1 = studentDao.query("1");
		//System.out.println("***********"+stu1.getName());
		/*
		System.out.println(SqlBuildUtil.buildQuerySql(Student.class));
		System.out.println(SqlBuildUtil.buildModifySql(Student.class));
		System.out.println(SqlBuildUtil.buildRemoveSql(Student.class));
		System.out.println(SqlBuildUtil.buildSaveSql(Student.class));*/
		/*redisApi.put("xiaolaohu", "哈哈哈好蠢");
		System.out.println(redisApi.get("xiaolaohu", String.class));*/
		
		/*System.out.println(dateReference.queryCurrentDate().getData());
		for(int i=0;i<100;i++){
			System.out.println(idReference.createId("ACY"));
		}
		
		System.out.println("**over**");*/
		/*ChatUserReqDto registerUserReqDto = new ChatUserReqDto();
		registerUserReqDto.setUsername("xiaohu4");
		registerUserReqDto.setPassword("xiaoer4");
		registerUserReqDto.setNickname("miaomiao4");
		List<ChatUserReqDto> registerUserReqDtos = new ArrayList<ChatUserReqDto>();
		registerUserReqDtos.add(registerUserReqDto);
		registerUserReqDto = new ChatUserReqDto();
		registerUserReqDto.setUsername("xiaohu5");
		registerUserReqDto.setPassword("xiaoer5");
		registerUserReqDto.setNickname("miaomiao5");
		registerUserReqDtos.add(registerUserReqDto);*/
		//System.out.println(chatUser.getUuid()+","+chatUser.getUsername());
		/*ChatUserReqDto registerUserReqDto = new ChatUserReqDto();
		registerUserReqDto.setUsername("18860367724_dev");
		registerUserReqDto.setPassword("123456");
		registerUserReqDto.setNickname("王小利");
		List<ChatUserReqDto> registerUserReqDtos = new ArrayList<ChatUserReqDto>();
		registerUserReqDtos.add(registerUserReqDto);*/
		/*registerUserReqDto = new ChatUserReqDto();
		registerUserReqDto.setUsername("15221126253_dev");
		registerUserReqDto.setPassword("123456");
		registerUserReqDto.setNickname("张伟伟");
		registerUserReqDtos.add(registerUserReqDto);*/
		//System.out.println(hxApi.registerUsers(registerUserReqDtos));
		/*ChatGroupReqDto chatGroupReqDto = new ChatGroupReqDto();
		
		chatGroupReqDto.setGroupname("我累群主");
		chatGroupReqDto.setDesc("我累群主");
		//特么的环信接口用public命名，转json时切忌把_public改成public
		chatGroupReqDto.set_public(false);
		chatGroupReqDto.setMaxusers(10);
		chatGroupReqDto.setApproval(false);
		chatGroupReqDto.setOwner("18860367724_test");
		List<String> members = new ArrayList<String>();
		members.add("13817005025_test");
		members.add("15899999997_test");
		members.add("13800000059_test");
		members.add("13818136279_test");
		members.add("18521359381_test");
		members.add("13661605761_test");
		members.add("18501791530_test");
		members.add("13127908868_test");
		members.add("18726909657_test");
		members.add("18221752109_test");
		chatGroupReqDto.setMembers(members);
		System.out.println(hxApi.createGroup(chatGroupReqDto));*/
		/*String userName = "13666606070_test";
		long beginTime = System.currentTimeMillis();
		ChatUser chatUser = hxApi.getUser(userName);
		long endTime = System.currentTimeMillis();
		System.out.println(endTime-beginTime);
		if(chatUser!=null){
			System.out.println(chatUser.getUsername());
		}*/
		
		/*String[] usernames = new String[]{"13817005025_test","13800000059_test","13818136279_test",
				"15899999997_test","18521359381_test"};
		ManyMemberReqDto manyMemberReqDto = new ManyMemberReqDto();
		manyMemberReqDto.setUsernames(usernames);
		hxApi.addUsers2group("17728879525890", manyMemberReqDto);*/
		//hxApi.addUser2group("11741470720002", "18860367724_dev");
		/*String[] usernames = new String[]{"bingyan","xiaoli"};
		ManyMemberReqDto manyMemberReqDto = new ManyMemberReqDto();
		manyMemberReqDto.setUsernames(usernames);
		ManyMember member =  (ManyMember) hxApi.addUsers2group("11741470720002", manyMemberReqDto);
		System.out.println(member.getNewmembers());*/
		/*String groupId = "11741470720002";
		List<String> userNames = new ArrayList<String>();
		userNames.add("bingyan");
		userNames.add("xiaoli");
		hxApi.removeUsers4group(groupId, userNames);*/
		/*String groupId = "11741470720002";
		List<String> userNames = new ArrayList<String>();
		userNames.add("xiaoli123");
		userNames.add("bingyan123");
		
		String[] usernames = new String[]{"bingyan","xiaoli"};
		ManyMemberReqDto manyMemberReqDto = new ManyMemberReqDto();
		manyMemberReqDto.setUsernames(usernames);
		ManyMember member =  (ManyMember) hxApi.addUsers2group("11741470720002", manyMemberReqDto);
		System.out.println(member.getNewmembers());*/
		/*testXmemcached_put();
		testXmemcached_get();*/
		/*String groupId = "12555545280513";
		TransferGroupReqDto transferGroupReqDto = new TransferGroupReqDto();
		transferGroupReqDto.setNewowner("18900000001_dev");
		TransferGroupData transferGroupData = hxApi.transferGroup(groupId, transferGroupReqDto);
		System.out.println(transferGroupData.isNewowner());*/
		/*testXmemcached_put();
		testXmemcached_get();*/
		
		context.close();
	}

}

