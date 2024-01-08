package com.javalec.admin;

import java.awt.EventQueue;

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

import com.javalec.sumin.function.AdminDao;
import com.javalec.sumin.function.AdminDto;

import javax.swing.event.ChangeEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Admin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel tabSales;
	private JPanel tabProduct;
	private JPanel tabMember;
	private final DefaultTableModel outerSalesTable = new DefaultTableModel();
	private final DefaultTableModel outerProductTable = new DefaultTableModel();
	private final DefaultTableModel outerMemberTable = new DefaultTableModel();
	private JPanel tabStock;
	private JComboBox cbProduct;
	private JTextField tfProduct;
	private JButton btnSearchProduct;
	private JScrollPane scrollPane;
	private JTable innerProductTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin frame = new Admin();
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
	public Admin() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				tableProductInit();
				searchProductAction();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 984, 554);
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
			tabbedPane.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					int selectedIndex = tabbedPane.getSelectedIndex();
					switch(selectedIndex) {
					case 0 : // 상품관리 tab
						tableProductInit();
						searchProductAction(); 
						break;
					case 1 : // 재고관리 tab
						tableStockInit();
						searchStockAction(); 
						break;
					case 2 : // 매출관리 tab
						tableStockInit();
						searchStockAction(); 
						break;	
					case 3 : // 회원관리 tab
						tableMemberInit();
						searchMemberAction(); 
						break;	
					default : break;
						
					}
				}
			});
			tabbedPane.addTab("상품관리", null, getTabProduct(), null);
			tabbedPane.addTab("재고관리", null, getTabStock(), null);
			tabbedPane.addTab("매출관리", null, getTabSales(), null);
			tabbedPane.addTab("회원관리", null, getTabMember(), null);
		}
		return tabbedPane;
	}
	private JPanel getTabSales() {
		if (tabSales == null) {
			tabSales = new JPanel();
			tabSales.setLayout(null);
		}
		return tabSales;
	}
	private JPanel getTabProduct() {
		if (tabProduct == null) {
			tabProduct = new JPanel();
			tabProduct.setLayout(null);
			tabProduct.add(getCbProduct());
			tabProduct.add(getTfProduct());
			tabProduct.add(getBtnSearchProduct());
			tabProduct.add(getScrollPane());
		}
		return tabProduct;
	}
	private JPanel getTabMember() {
		if (tabMember == null) {
			tabMember = new JPanel();
		}
		return tabMember;
	}
	private JPanel getTabStock() {
		if (tabStock == null) {
			tabStock = new JPanel();
		}
		return tabStock;
	}
	// 여기까지 기본에서 Tab까지 구현한 화면 
	
	private JComboBox getCbProduct() {
		if (cbProduct == null) {
			cbProduct = new JComboBox();
			cbProduct.setBounds(40, 23, 159, 23);
		}
		return cbProduct;
	}
	private JTextField getTfProduct() {
		if (tfProduct == null) {
			tfProduct = new JTextField();
			tfProduct.setBounds(211, 24, 288, 21);
			tfProduct.setColumns(10);
		}
		return tfProduct;
	}
	private JButton getBtnSearchProduct() {
		if (btnSearchProduct == null) {
			btnSearchProduct = new JButton("검색");
			btnSearchProduct.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
			btnSearchProduct.setBounds(511, 23, 97, 23);
		}
		return btnSearchProduct;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(40, 57, 871, 331);
			scrollPane.setViewportView(getInnerProductTable());
		}
		return scrollPane;
	}
	private JTable getInnerProductTable() {
		if (innerProductTable == null) {
			innerProductTable = new JTable();
		}
		return innerProductTable;
	} // 여기까지 상품관리 화면.
	
	// [상품관리] ===== Function ========================
	private void tableProductInit() {
		// Table Column 명 정하기.
		outerProductTable.addColumn("상품명");
		outerProductTable.addColumn("상품가격");
		outerProductTable.addColumn("설명");
		outerProductTable.addColumn("영양정보");
		outerProductTable.addColumn("성분");
		outerProductTable.addColumn("이미지");
		outerProductTable.setColumnCount(6);
		// 상품명
		int colNo = 0;
		TableColumn col = innerProductTable.getColumnModel().getColumn(colNo);
		int width = 50;
		col.setPreferredWidth(width);
		
		// 상품가격
		colNo = 1;
		col = innerProductTable.getColumnModel().getColumn(colNo);
		width = 80;
		col.setPreferredWidth(width);

		
		// 설명
		colNo = 2;
		col = innerProductTable.getColumnModel().getColumn(colNo);
		width = 80;
		col.setPreferredWidth(width);
		
		
		// 영양정보
		colNo = 3;
		col = innerProductTable.getColumnModel().getColumn(colNo);
		width = 50;
		col.setPreferredWidth(width);
		
		// 성분
		colNo = 4;
		col = innerProductTable.getColumnModel().getColumn(colNo);
		width = 80;
		col.setPreferredWidth(width);
		
		
		// 이미지
		colNo = 5;
		col = innerProductTable.getColumnModel().getColumn(colNo);
		width = 150;
		col.setPreferredWidth(width);
		
		
		// 테이블 내용 지우기.
		int i = innerProductTable.getRowCount();
		for(int j=0; j<i; j++) {
			outerProductTable.removeRow(0);
		}
		
		innerProductTable.setAutoResizeMode(innerProductTable.AUTO_RESIZE_OFF);
		
	}
	
	private void searchProductAction() {
		AdminDao dao = new AdminDao(srhDate);
		ArrayList<AdminDto> dtoList = dao.selectTodayList();
		
		int totPrice = 0;		
		int listCount = dtoList.size();
		for(int i=0; i<listCount; i++) {
			int purnum = dtoList.get(i).getPurnum();
			String custid = dtoList.get(i).getCustid();
			String stomodelnum = dtoList.get(i).getStomodelnum();
			int purqty = dtoList.get(i).getPurqty();
			int purprice = dtoList.get(i).getPurprice(); 
			String purdate = dtoList.get(i).getPurdate();
			
			String tmpPurnum = Integer.toString(purnum);
			String tmpPurqty = Integer.toString(purqty);
			String tmpPurprice = df.format(purprice);
			totPrice += purprice;
			String[] qTxt = {tmpPurnum, custid, stomodelnum, tmpPurqty, tmpPurprice, purdate};
			outerTable.addRow(qTxt);
		}
		
		// Table Column별 정렬하기.
		// Table Column(Cell) 가운데 정렬
		DefaultTableCellRenderer center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(SwingConstants.CENTER);
		
		TableColumnModel tcm = innerTable.getColumnModel();
		
		// 특정 Column(Cell) 가운데 정렬
		tcm.getColumn(0).setCellRenderer(center);
		tcm.getColumn(1).setCellRenderer(center);
		tcm.getColumn(2).setCellRenderer(center);
		tcm.getColumn(3).setCellRenderer(center);
		
		// Table Column(Cell) 우측 정렬
		DefaultTableCellRenderer right = new DefaultTableCellRenderer();
		right.setHorizontalAlignment(SwingConstants.RIGHT);
		
		// 특정 Column(Cell) 우측 정렬
		tcm.getColumn(4).setCellRenderer(right);
		tcm.getColumn(5).setCellRenderer(center);		
		
		String strPrice = df.format(totPrice);
		tfTotal.setText(strPrice);
	}	// End of searchAction()
	
}
