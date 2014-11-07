	===================2014.11.06 update=====================

	将ProgressDialog封装进了NetworkHelper中。
	现在进行网络通信方法：
	1.创建一个NetworkHelper的对象
	2.实现HandleUI的接口，用于当服务器返回数据时，处理UI
	3.调用handleObject/handleString方法，将相关参数传递进去（暂时只支持了String的交换，之后将更新其他方法，支持更多数据格式）
	
	===================2014.11.06 update end=====================

1.所有的activity继承cm.getuptogether.activity.BaseActivity。Dao继承cm.getuptogether.dao.BaseDao。

2.如何使用AndroidAnnotation:
	http://blog.csdn.net/limb99/article/details/9067827
	https://github.com/excilys/androidannotations/wiki
	http://www.itnose.net/detail/6098386.html
	例子参照cm.getuptogether.activity.MainActivity中布局的设置以及控件的声明。
	

3.如何在Activity中使用ormlite进行增删改查
	3.1 新建实体类。每个实体类对应一张表。参照cm.getuptogether.bean.Test。
	3.1 新建实体类的dao文件，继承cm.getuptogether.dao.BaseDao。在BaseDao中定义了基本的增删改查的操作。如果需要复杂的增删改查，在新建的实体类中完成即可。
	3.2 在BaseActivity中已定义了dao。所以在子类的activity中只需要new一个对应实体类的对象出来即可操作对应实体类的表。
	3.3 在activity中利用实体类的dao文件进行sqlite的增删改查

	System.out.println("===============test insert");
	System.out.println("insert:1 1 ");
	dao.insert(new Test(1, 1));
	System.out.println("insert:2 2");
	dao.insert(new Test(1, 2));

	 System.out.println("===============test query");
	 System.out.println("query state=1");
	 for (Test test : dao.query("state", "1")) {
	 System.out.println(test);
	 }
	 System.out.println("query all");
	 for (Test test : dao.queryAll()) {
	 System.out.println(test);
	 }
	 System.out.println("insert all");
	List<Test> ls = new ArrayList<Test>();
	ls.add(new Test(1, 3));
	ls.add(new Test(1, 4));
	dao.insert(ls);
	 System.out.println("query all");
	 for (Test test : dao.queryAll()) {
	 System.out.println(test);
	 }
	
	 System.out.println("query userprofileId=1,state=1");
	 for (Test test : dao.query(new String[] { "userprofileId",
	 "state" }, new String[] { "1", "1" })) {
	 System.out.println(test);
	 }
	 System.out.println("query userprofileId=2,state=1");
	 for (Test test : dao.query(new String[] { "userprofileId",
	 "state" }, new String[] { "2", "1" })) {
	 System.out.println(test);
	 }
	
	 Map<String, Object> map = new HashMap<String, Object>();
	 map.put("userprofileId", 4);
	 for (Test test : dao.query(map)) {
	 System.out.println(test);
	 }
	
	 Map<String, Object> map = new HashMap<String, Object>();
	 map.put("state", 1);
	 Map<String, Object> low = new HashMap<String, Object>();
	 low.put("userprofileId", 1);
	 Map<String, Object> high = new HashMap<String, Object>();
	 high.put("userprofileId", 4);
	 for (Test test : dao.query(map, low, high)) {
	 System.out.println(test);
	 }

	 System.out.println("===============test update");
	 List<Test> list = dao.queryAll();
	
	 Test test_o = list.get(0);
	
	 test_o.setState(555);
	
	 dao.update(test_o);
	 System.out.println("query all");
	 for (Test test : dao.queryAll()) {
	 System.out.println(test);
	 }

	 System.out.println("======================test delete");
	 System.out.println("query all");
	 for (Test test : dao.queryAll()) {
	 System.out.println(test);
	 }
	 System.out.println("delete state=3");
	 dao.delete("state", "3");
	 for (Test test : dao.queryAll()) {
	 System.out.println(test);
	 }
	 System.out.println("delete state=2 userprofileId=56");
	 dao.delete(new String[]{"state","userprofileId"}, new
	 String[]{"2","56"});
	 for (Test test : dao.queryAll()) {
	 System.out.println(test);
	 }
	 
4.如何使用Volley进行网络通信（@Deprecated）
	直接从BaseActivity中拿出VolleyTools对象，然后参照cm.getuptogether.activity.MainActivity中。

5.在BaseActivity中还定义了Gson，直接就可以使用。



	
	
	
	
	