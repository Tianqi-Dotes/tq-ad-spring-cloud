import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tq.ad.dump.SqlConstant;
import com.tq.ad.dump.table.AdPlanTable;
import com.tq.ad.dump.table.AdUnitTable;
import com.tq.ad.entity.AdPlan;
import com.tq.ad.entity.AdUnit;
import com.tq.ad.service.IAdPlanService;
import com.tq.ad.service.IAdUnitService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DdApplicationTests {

	@Autowired
	IAdPlanService adPlanService;

	@Autowired
	IAdUnitService adUnitService;

	@Test
	public void contextLoads() throws IOException, URISyntaxException, ParseException, ClassNotFoundException, IllegalAccessException, InstantiationException {

		dumpAdPlans((String.format("%s%s", SqlConstant.DATA_ROOT_DIR,SqlConstant.AD_PLAN)));
		dumpAdUnit((String.format("%s%s", SqlConstant.DATA_ROOT_DIR,SqlConstant.AD_UNIT)));

	}

	private void dumpAdPlans(String fileName){

		QueryWrapper<AdPlan> queryWrapper=new QueryWrapper<>();
		queryWrapper.eq("plan_status",1);
		List<AdPlan> adPlans=adPlanService.list(queryWrapper);

		List<AdPlanTable> adPlanTable=new ArrayList<>();
		adPlans.stream().forEach(plan->adPlanTable.add(
				new AdPlanTable(plan.getId(),plan.getUserId(), plan.getPlanStatus(),plan.getStartDate(),plan.getEndDate())
		));

		Path path= Paths.get(fileName);
		try {
			BufferedWriter writer= Files.newBufferedWriter(path);
			for(AdPlanTable table:adPlanTable){
				writer.write(JSON.toJSONString(table));
				writer.newLine();
			}
			writer.close();
		}catch (IOException e){
			log.error("ad plan dump error");
		}
	}

	private void dumpAdUnit(String fileName){

		QueryWrapper<AdUnit> queryWrapper=new QueryWrapper<>();
		queryWrapper.eq("unit_status",1);
		List<AdUnit> adUnits=adUnitService.list(queryWrapper);

		List<AdUnitTable> adPlanTable=new ArrayList<>();
		adUnits.stream().forEach(unit->adPlanTable.add(
				new AdUnitTable(unit.getId(),unit.getUnitStatus(), unit.getPositionType(),unit.getPlanId())
		));

		Path path= Paths.get(fileName);
		try {
			BufferedWriter writer= Files.newBufferedWriter(path);
			for(AdUnitTable table:adPlanTable){
				writer.write(JSON.toJSONString(table));
				writer.newLine();
			}
			writer.close();
		}catch (IOException e){
			log.error("ad plan dump error");
		}
	}
}
