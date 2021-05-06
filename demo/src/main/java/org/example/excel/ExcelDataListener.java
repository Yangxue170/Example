//package com.ke.sun.biz.file;
//
//import com.alibaba.excel.context.AnalysisContext;
//import com.alibaba.excel.event.AnalysisEventListener;
//import com.alibaba.fastjson.JSON;
//import com.baomidou.mybatisplus.extension.service.IService;
//import lombok.extern.slf4j.Slf4j;
//
//import java.util.ArrayList;
//import java.util.List;
//
// todo easyExcel的jar包进行处理使用
///**
// * @author Jdx
// * @version 1.0
// * @description TODO默认第一行是标题，读取数据的顺序是按照定义字段的顺序，需要将字段顺序与标题顺序一致
// * @date 2021/4/26 11:30
// */
//@Slf4j
//public class ExcelDataListener<T> extends AnalysisEventListener<T> {
//	/**
//	 * 每隔1000条存储数据库，然后清理list ，方便内存回收
//	 */
//	private static final int BATCH_COUNT = 1000;
//	private final List<T> dataList = new ArrayList<>();
//
//	private final IService<T> tiService;
//
//	public ExcelDataListener(IService<T> tiService) {
//		this.tiService = tiService;
//	}
//
//
//	@Override
//	public void invoke(T data, AnalysisContext context) {
//		log.info("解析到一条数据:{}", JSON.toJSONString(data));
//		dataList.add(data);
//		// 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
//		if (dataList.size() >= BATCH_COUNT) {
//			saveData();
//		}
//	}
//
//
//	@Override
//	public void doAfterAllAnalysed(AnalysisContext context) {
//		// 这里也要保存数据，确保最后遗留的数据也存储到数据库
//		saveData();
//		log.info("所有数据解析完成！");
//	}
//
//	private void saveData() {
//		log.info("{}条数据，开始存储数据库！", dataList.size());
//		tiService.saveBatch(dataList);
//		log.info("存储数据库成功！");
//		// 存储完成清理 list
//		dataList.clear();
//	}
//}
