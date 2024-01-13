package com.javalec.admin;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import com.javalec.common.ShareVar;
import com.javalec.function.AdminDAO;
import com.javalec.function.ProductDAO;
import com.javalec.function.SalesDAO;
import com.javalec.model.ProductDTO;

import javax.swing.event.ChangeEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SalesList extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel tabSales;
	private JPanel tabMonth;
	private JPanel tabMember;
	private final DefaultTableModel outerSalesTable = new DefaultTableModel();
	private final DefaultTableModel outerProductTable = new DefaultTableModel();
	private final DefaultTableModel outerMemberTable = new DefaultTableModel();
	private DecimalFormat df = new DecimalFormat("###,###,###,###");
	private JButton btnItem;
	private JButton btnStock;
	private JButton btnMember;
	private JButton btnPurchase;
	private JButton btnProductList;
	private JPanel panel;
	private JPanel tabDay;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalesList frame = new SalesList();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SalesList() {
		setTitle("매출관리");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 985, 555);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getTabbedPane());
	}

	private JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBounds(12, 10, 944, 492);

			tabbedPane.addTab("월별매출", null, getTabMonth(), null);
			tabbedPane.addTab("일별매출", null, getTabDay(), null);
		}
		return tabbedPane;
	}

	private JPanel getTabMonth() {
		if (tabMonth == null) {
			tabMonth = new JPanel();
			tabMonth.setLayout(null);
			tabMonth.add(getBtnItem());
			tabMonth.add(getBtnStock());
			tabMonth.add(getBtnMember());
			tabMonth.add(getBtnPurchase());
			tabMonth.add(getBtnProductList());
			tabMonth.add(getPanel());
		}
		return tabMonth;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(24, 19, 887, 374);
			panel.setLayout(null);
			JFreeChart chart = getMonthlyPriceCart();
//			JFreeChart chart = ChartFactory.createGanttChart("Chart Example","X Label","Y Label",null);
//			JFreeChart chart = ChartFactory.createBarChart(getTitle(), getWarningString(), getName(), null);
//			JFreeChart chart = ChartFactory.createBarChart("월별매출", // title
//				    resourceMap.getString("graphDate.text"), // x-axis label
//				    resourceMap.getString("graphCBills.text"), // y-axis label
//				    dataset);
			
			ChartPanel chartPanel = new ChartPanel(chart);
			
			chartPanel.setBounds(12, 10, 867, 354);
			panel.add(chartPanel);
		}
		return panel;
	}
	private JPanel getTabDay() {
		if (tabDay == null) {
			tabDay = new JPanel();
		}
		return tabDay;
	}
	
	
	
	// ==================== 아래 메뉴 ================================
	private JButton getBtnProductList() {
		if (btnProductList == null) {
			btnProductList = new JButton("상품관리");
			btnProductList.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gotoProductList();
				}
			});
			btnProductList.setBounds(40, 417, 146, 23);
		}
		return btnProductList;
	}
	private JButton getBtnItem() {
		if (btnItem == null) {
			btnItem = new JButton("상품종류 관리");
			btnItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gotoItemList();
				}
			});
			btnItem.setBounds(216, 417, 146, 23);
		}
		return btnItem;
	}
	private JButton getBtnStock() {
		if (btnStock == null) {
			btnStock = new JButton("상품재고 관리");
			btnStock.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gotoStockList();
				}
			});
			btnStock.setBounds(400, 417, 146, 23);
		}
		return btnStock;
	}
	private JButton getBtnMember() {
		if (btnMember == null) {
			btnMember = new JButton("회원 관리");
			btnMember.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gotoMemberList();
				}
			});
			btnMember.setBounds(590, 417, 146, 23);
		}
		return btnMember;
	}
	private JButton getBtnPurchase() {
		if (btnPurchase == null) {
			btnPurchase = new JButton("매출관리");
			btnPurchase.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gotoSalesList();
				}
			});
			btnPurchase.setBounds(765, 417, 146, 23);
		}
		return btnPurchase;
	}
	// ==================== 아래 메뉴 ================================

	// 월별 매출금액 불러오기.
	private JFreeChart getMonthlyPriceCart() {
		
		SalesDAO salesDAO = new SalesDAO();
		JFreeChart monthlyPriceChart = salesDAO.getChart();
		
		return monthlyPriceChart;
	}	// End of searchAction()


	
	// =========== 아래버튼 ========================
	// 현재 창을 닫고 상품관리 페이지로 이동.
	private void gotoProductList(){
		this.setVisible(false);
		ProductList productList = new ProductList();
		productList.main(null);
	}
	// 현재 창을 닫고 상품분류 페이지로 이동.
	private void gotoItemList(){
		this.setVisible(false);
		ItemList itemList = new ItemList();
		itemList.main(null);
	}
	// 현재 창을 닫고 재고관리 페이지로 이동.
	private void gotoStockList(){
		this.setVisible(false);
		StockList stockList = new StockList();
		stockList.main(null);
	}
	// 현재 창을 닫고 회원관리 페이지로 이동.
	private void gotoMemberList(){
		this.setVisible(false);
		MemberList memberList = new MemberList();
		memberList.main(null);
	}
	// 현재 창을 닫고 매출관리 페이지로 이동.
	private void gotoSalesList(){
		this.setVisible(false);
		SalesList salesList = new SalesList();
		salesList.main(null);
	}
	// =========== 아래버튼 ========================



}
