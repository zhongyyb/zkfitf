package nc.impl.itfzkf.basedata;

import java.util.List;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.itf.itfzkf.basedata.IAccassitemService;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.util.itfzkf.pub.ZkungfuLogger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.ValueFilter;
/**
 * ��Ŀ���������ӿ�ʵ����
 * @author zhongyanying
 *
 */
public class AccassitemServiceImpl implements IAccassitemService {
	
	private final ZkungfuLogger log = new ZkungfuLogger("AccassitemServiceImpl");
	private BaseDAO baseDAO = new BaseDAO();
	
	/**
	 * ����ͼ��ȡ��Ŀ�������װ��json����
	 */
	@SuppressWarnings("unchecked")
	public String getSubjassFromView() throws DAOException {
		log.info("########### Begin: Call getSubjassFromView method begin ###########");
		
		StringBuilder sql = new StringBuilder();
		sql.append("select pk_corp, pk_subjass, pk_accsubj, isbalancecontrol, issumprint, bdname, bdcode, pk_bdinfo, ts from v_nc_bd_subjass");
		List<Map<String,Object>> subjassMapList = (List<Map<String, Object>>) baseDAO.executeQuery(sql.toString(), new MapListProcessor());
		
		/*JsonConfig jsonConfig= new JsonConfig();  
		
        
	    //  ����String����Ϊ�յ��ַ�����ʽ  
	    jsonConfig.registerDefaultValueProcessor(Map.class, new DefaultValueProcessor() {  
	        @Override  
	        public Object getDefaultValue(Class type) {  
	            return "";  
	        }  
	    });  */

		JSONArray array = new JSONArray();
		for(Map<String,Object> subjass : subjassMapList) {
			//net.sf.json.JSONObject a = net.sf.json.JSONObject.fromObject(subjass, jsonConfig);
			String accassitem  = JSONObject.toJSONString(subjass, filter);
			array.add(accassitem);
		}
		
		//����
		JSONObject resultObj = new JSONObject();
		resultObj.put("subjassList", array);
		
		//net.sf.json.JSONObject d = net.sf.json.JSONObject.fromObject(resultObj.toString());//for test
		log.info("########### End: Call getSubjassFromView method end ###########");
		return resultObj.toString();
	}
	
	/**
	 * ���������޸�null��valueΪ""
	 */
	private ValueFilter filter = new ValueFilter() {
	    @Override
	    public Object process(Object obj, String s, Object v) {
	    if(v==null)
	        return "";
	    return v;
	    }
	};

}
