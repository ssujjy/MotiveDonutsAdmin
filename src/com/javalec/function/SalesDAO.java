package com.javalec.function;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.category.DefaultCategoryDataset;

import com.javalec.common.ShareVar;
import com.javalec.model.ProductDTO;

public class SalesDAO {
	// Field
	private final String url_mysql = ShareVar.dbName;
	private final String id_mysql = ShareVar.dbUser;
	private final String pw_mysql = ShareVar.dbPass;
	
	// Constructor
	public SalesDAO() {
		// TODO Auto-generated constructor stub
	}
	
	// Method
	// 한자리 숫자 앞에 + 0 예) 7일 -> 07일  
	private String addZeroString(int k){
		String value=Integer.toString(k);
		if(value.length()==1) {
			value="0"+value;
		}
		return value;
	}
	// 매출수량별 Bar 차트 그려주는 메소드
	public JFreeChart getQTYChart() {
		// 데이터 생성
		DefaultCategoryDataset datasetMonthlyQTY = new DefaultCategoryDataset(); // 월별 판매수량 그래프

		for(int i=1; i<=12; i++) {
			int monthlyQTYValue = selectQTYAction("2024-"+addZeroString(i));	// 매월 판매수량 합계 값 가져오기.
			datasetMonthlyQTY.addValue(monthlyQTYValue, "QTY", addZeroString(i)+"월");	// 월별 판매수량 그래프에 넣기.
			
		}
		
		// 렌더링 생성 및 세팅
		// 렌더링 생성
		final BarRenderer rendererQTY = new BarRenderer();	// 매출수량

		// 공통 옵션 정의
		final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
		final ItemLabelPosition p_center = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER);
		final ItemLabelPosition p_below = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_LEFT);
		Font f = new Font("Gulim", Font.BOLD, 14);
		Font axisF = new Font("Gulim", Font.PLAIN, 14);
		
		
		// 렌더링 세팅
		// 월별 매출수량 그래프
		rendererQTY.setDefaultItemLabelGenerator(generator);
		rendererQTY.setDefaultItemLabelsVisible(true);
		rendererQTY.setDefaultPositiveItemLabelPosition(p_below);
		rendererQTY.setDefaultItemLabelFont(f);
		rendererQTY.setSeriesPaint(0, new Color(232,168,255));

		// plot 생성
		final CategoryPlot plot = new CategoryPlot();
		// plot 에 데이터 적재
		plot.setDataset(datasetMonthlyQTY);	//plot에 월별 매출수량 데이터 적재
		plot.setRenderer(rendererQTY);	//plot에 월별 매출수량 데이터 적재
		
		// plot 기본 설정
		plot.setOrientation(PlotOrientation.VERTICAL);	// 그래프 표시 방향
		plot.setRangeGridlinesVisible(true);	// X축 가이드 라인 표시여부
		plot.setDomainGridlinesVisible(true);	// Y축 가이드 라인 표시여부
		
		// 렌더링 순서 정의 : dataset 등록 순서대로 렌더링 ( 즉, 먼저 등록한게 아래로 깔림 )
		plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
		
		// X축 세팅
		plot.setDomainAxis(new CategoryAxis()); // X축 종류 설정
		plot.getDomainAxis().setTickLabelFont(axisF); // X축 눈금라벨 폰트 조정
		plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.STANDARD);	// 카테고리 라벨 위치 조정
		// Y축 세팅
		plot.setRangeAxis(new NumberAxis());	// Y축 종류 설정
		plot.getRangeAxis().setTickLabelFont(axisF); // Y축 눈금라벨 폰트 조정

		// 세팅된 plot을 바탕으로 chart 생성
		final JFreeChart chart = new JFreeChart(plot);

		return chart;
	}
	
	// 매출금액별 Bar 차트 그려주는 메소드
		public JFreeChart getPriceChart() {
			// 데이터 생성
			DefaultCategoryDataset datasetMonthlyPrice = new DefaultCategoryDataset(); // 월별 합계금액 그래프

			for(int i=1; i<=12; i++) {
				int monthlyPriceValue = selectPaypriceAction("2024-"+addZeroString(i));	// 매월 금액 합계 값 가져오기.
				datasetMonthlyPrice.addValue(monthlyPriceValue, "AMOUNT", addZeroString(i)+"월");	// 월별 매출금액 그래프에 넣기.
			}
			
			// 렌더링 생성 및 세팅
			// 렌더링 생성
			final BarRenderer rendererPrice = new BarRenderer();	// 매출금액

			// 공통 옵션 정의
			final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
			final ItemLabelPosition p_center = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER);
			final ItemLabelPosition p_below = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_LEFT);
			Font f = new Font("Gulim", Font.BOLD, 14);
			Font axisF = new Font("Gulim", Font.PLAIN, 14);
			
			
			// 렌더링 세팅
			// 월별 매출 그래프
			rendererPrice.setDefaultItemLabelGenerator(generator);
			rendererPrice.setDefaultItemLabelsVisible(true);
			rendererPrice.setDefaultPositiveItemLabelPosition(p_center);
			rendererPrice.setDefaultItemLabelFont(f);
			rendererPrice.setSeriesPaint(0, new Color(0,162,255));

			// plot 생성
			final CategoryPlot plot = new CategoryPlot();
			// plot 에 데이터 적재
			plot.setDataset(datasetMonthlyPrice);	//plot에 월별 매출금액 데이터 적재
			plot.setRenderer(rendererPrice);	// plot에 월별 매출금액 데이터 적재

			// plot 기본 설정
			plot.setOrientation(PlotOrientation.VERTICAL);	// 그래프 표시 방향
			plot.setRangeGridlinesVisible(true);	// X축 가이드 라인 표시여부
			plot.setDomainGridlinesVisible(true);	// Y축 가이드 라인 표시여부
			
			// 렌더링 순서 정의 : dataset 등록 순서대로 렌더링 ( 즉, 먼저 등록한게 아래로 깔림 )
			plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
			
			// X축 세팅
			plot.setDomainAxis(new CategoryAxis()); // X축 종류 설정
			plot.getDomainAxis().setTickLabelFont(axisF); // X축 눈금라벨 폰트 조정
			plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.STANDARD);	// 카테고리 라벨 위치 조정
			// Y축 세팅
			plot.setRangeAxis(new NumberAxis());	// Y축 종류 설정
			plot.getRangeAxis().setTickLabelFont(axisF); // Y축 눈금라벨 폰트 조정

			// 세팅된 plot을 바탕으로 chart 생성
			final JFreeChart chart = new JFreeChart(plot);

			return chart;
		}
	
	public int selectQTYAction(String date){
		int sumOfMonthlQTY = 0;
		
		String whereDefault = "SELECT sum(purqty) FROM purchase ";
		String where = "WHERE purdate like '" + date + "%'";
		System.out.println(whereDefault+where);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			ResultSet rs = stmt_mysql.executeQuery(whereDefault+where);
			
			while(rs.next()) {	
				sumOfMonthlQTY = rs.getInt(1);
			}
			conn_mysql.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return sumOfMonthlQTY;
	}	// End of selectQTYAction
	
	public int selectPaypriceAction(String date){
		int sumOfMonthlyprice = 0;
		
		String whereDefault = "SELECT sum(payprice) FROM myorder ";
		String where = "WHERE orderdate like '" + date + "%'";
		System.out.println(whereDefault+where);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			ResultSet rs = stmt_mysql.executeQuery(whereDefault+where);
			
			while(rs.next()) {	
				sumOfMonthlyprice = rs.getInt(1);
			}
			conn_mysql.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return sumOfMonthlyprice;
	}	// selectPaypriceAction
//	double[] Arr_Data1 = new double[12]; //전체
}
