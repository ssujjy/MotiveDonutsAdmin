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

import com.javalec.common.ShareVar;
import com.javalec.function.AdminDAO;
import com.javalec.function.ProductDAO;
import com.javalec.function.StockDAO;
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
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StockList extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel tabSales;
	private JPanel tabProduct;
	private JPanel tabMember;
	private final DefaultTableModel outerSalesTable = new DefaultTableModel();
	private final DefaultTableModel outerProductTable = new DefaultTableModel();
	private final DefaultTableModel outerMemberTable = new DefaultTableModel();
	private DecimalFormat df = new DecimalFormat("###,###,###,###");
	private JPanel tabStock;
	private JComboBox cbProduct;
	private JTextField tfProduct;
	private JButton btnSearchProduct;
	private JScrollPane scrollPane;
	private JTable innerProductTable;
	private ArrayList<ProductDTO> dtoList = null;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btnItem;
	private JButton btnStock;
	private JButton btnMember;
	private JButton btnPurchase;
	private JButton btnProductList;
	private JTextField tfQTY;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_3_1;
	private JTextField tfProname;
	private JButton btnAddQTY;
	private JLabel lblNewLabel_3_1_1;
	private JTextField tfItem;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StockList frame = new StockList();
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
	public StockList() {
		setTitle("재고관리");
		addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowActivated(WindowEvent e) {
//				removeComboCategory();
//				getComboCategory();
//				tableProductInit();
//				searchProductAction();
//			}
			@Override
			public void windowOpened(WindowEvent e) {
				removeComboCategory();
				getComboCategory();
				tableProductInit();
				searchProductAction();
			}
			@Override
			public void windowClosing(WindowEvent e) {
				closingAction();
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
			tabbedPane.addTab("상품목록", null, getTabProduct(), null);
		}
		return tabbedPane;
	}

	private JPanel getTabProduct() {
		if (tabProduct == null) {
			tabProduct = new JPanel();
			tabProduct.setLayout(null);
			tabProduct.add(getCbProduct());
			tabProduct.add(getTfProduct());
			tabProduct.add(getBtnSearchProduct());
			tabProduct.add(getScrollPane());
			tabProduct.add(getLblNewLabel());
			tabProduct.add(getLblNewLabel_1());
			tabProduct.add(getBtnItem());
			tabProduct.add(getBtnStock());
			tabProduct.add(getBtnMember());
			tabProduct.add(getBtnPurchase());
			tabProduct.add(getBtnProductList());
			tabProduct.add(getTfQTY());
			tabProduct.add(getLblNewLabel_2());
			tabProduct.add(getLblNewLabel_3());
			tabProduct.add(getLblNewLabel_3_1());
			tabProduct.add(getTfProname());
			tabProduct.add(getBtnAddQTY());
			tabProduct.add(getLblNewLabel_3_1_1());
			tabProduct.add(getTfItem());
		}
		return tabProduct;
	}

	// 여기까지 기본에서 Tab까지 구현한 화면 
	
	private JComboBox getCbProduct() {
		if (cbProduct == null) {
			cbProduct = new JComboBox();
			cbProduct.setBounds(110, 19, 159, 23);
		}
		return cbProduct;
	}
	private JTextField getTfProduct() {
		if (tfProduct == null) {
			tfProduct = new JTextField();
			tfProduct.setBounds(335, 20, 159, 21);
			tfProduct.setColumns(10);
		}
		return tfProduct;
	}
	private JButton getBtnSearchProduct() {
		if (btnSearchProduct == null) {
			btnSearchProduct = new JButton("검색");
			btnSearchProduct.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tableProductInit();
					searchProductAction(); 
				}
			});
			btnSearchProduct.setBounds(506, 19, 97, 23);
		}
		return btnSearchProduct;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(40, 57, 598, 331);
			scrollPane.setViewportView(getInnerProductTable());
		}
		return scrollPane;
	}
	private JTable getInnerProductTable() {
		if (innerProductTable == null) {
			innerProductTable = new JTable(){ 								
				public Class getColumnClass(int column) { 				
			        return (column == 0) ? Icon.class : Object.class; 	
			      }
			};
			innerProductTable.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					tableClick();
				}
			});
			innerProductTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			innerProductTable.setRowHeight(150); 	
			innerProductTable.setModel(outerProductTable);
			
		}
		return innerProductTable;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("상품종류 : ");
			lblNewLabel.setBounds(51, 23, 69, 15);
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("상품명 : ");
			lblNewLabel_1.setBounds(281, 23, 69, 15);
		}
		return lblNewLabel_1;
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
	private JTextField getTfQTY() {
		if (tfQTY == null) {
			tfQTY = new JTextField();
			tfQTY.setBounds(757, 152, 116, 21);
			tfQTY.setColumns(10);
		}
		return tfQTY;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("개");
			lblNewLabel_2.setBounds(885, 155, 26, 15);
		}
		return lblNewLabel_2;
	}
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("추가할 갯수 : ");
			lblNewLabel_3.setBounds(650, 155, 95, 15);
		}
		return lblNewLabel_3;
	}
	private JLabel getLblNewLabel_3_1() {
		if (lblNewLabel_3_1 == null) {
			lblNewLabel_3_1 = new JLabel("상품명 : ");
			lblNewLabel_3_1.setBounds(650, 60, 95, 15);
		}
		return lblNewLabel_3_1;
	}
	private JTextField getTfProname() {
		if (tfProname == null) {
			tfProname = new JTextField();
			tfProname.setEditable(false);
			tfProname.setColumns(10);
			tfProname.setBounds(757, 57, 154, 21);
		}
		return tfProname;
	}
	private JButton getBtnAddQTY() {
		if (btnAddQTY == null) {
			btnAddQTY = new JButton("재고 추가하기");
			btnAddQTY.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					insertQTYAction();
				}
			});
			btnAddQTY.setBounds(674, 248, 199, 23);
		}
		return btnAddQTY;
	}
	private JLabel getLblNewLabel_3_1_1() {
		if (lblNewLabel_3_1_1 == null) {
			lblNewLabel_3_1_1 = new JLabel("상품분류 : ");
			lblNewLabel_3_1_1.setBounds(650, 106, 95, 15);
		}
		return lblNewLabel_3_1_1;
	}
	private JTextField getTfItem() {
		if (tfItem == null) {
			tfItem = new JTextField();
			tfItem.setEditable(false);
			tfItem.setColumns(10);
			tfItem.setBounds(757, 103, 154, 21);
		}
		return tfItem;
	}
	// ==================== 아래 메뉴 ================================
	
	// [상품관리] ===== Function ========================
	private void tableProductInit() { //			proname, sellprice, detail, nutritional, ingredient, image, imagename, wItem
		// Table Column 명 정하기.
		outerProductTable.addColumn("이미지");
		outerProductTable.addColumn("상품명");
		outerProductTable.addColumn("상품가격");
		outerProductTable.addColumn("설명");
		outerProductTable.addColumn("분류");
		outerProductTable.setColumnCount(5);
		// 이미지
		int colNo = 0;
		TableColumn col = innerProductTable.getColumnModel().getColumn(colNo);
		int width = 150;
		col.setPreferredWidth(width);
		
		// 상품명
		colNo = 1;
		col = innerProductTable.getColumnModel().getColumn(colNo);
		width = 150;
		col.setPreferredWidth(width);
		
		// 상품가격
		colNo = 2;
		col = innerProductTable.getColumnModel().getColumn(colNo);
		width = 80;
		col.setPreferredWidth(width);

		
		// 설명
		colNo = 3;
		col = innerProductTable.getColumnModel().getColumn(colNo);
		width = 350;
		col.setPreferredWidth(width);
		
		// 분류
		colNo = 4;
		col = innerProductTable.getColumnModel().getColumn(colNo);
		width = 50;
		col.setPreferredWidth(width);
		
		// 테이블 내용 지우기.
		int i = innerProductTable.getRowCount();
		for(int j=0; j<i; j++) {
			outerProductTable.removeRow(0);
		}
		
		innerProductTable.setAutoResizeMode(innerProductTable.AUTO_RESIZE_OFF);
		
	}
	// 카테고리 가져오기.
	private void getComboCategory() {
		ProductDAO prodcutDAO = new ProductDAO();
		ArrayList<String> listCategory = prodcutDAO.selectItem();
//		String[] strCategory = new listCategory.get(0);
		
		for(int i=0; i<listCategory.size(); i++) {
			cbProduct.addItem(listCategory.get(i));
		}
	}
	// 카테고리 초기화.
	private void removeComboCategory() {
		int cntCategory = cbProduct.getItemCount();
		for(int i=0; i<cntCategory; i++) {
			cbProduct.remove(i);
		}
	}
	// 상품관리 테이블 불러오기.
	private void searchProductAction() {
		String item = (String) cbProduct.getSelectedItem();
		String val = tfProduct.getText();
		
		ProductDAO prodcutDAO = new ProductDAO();
		dtoList = prodcutDAO.selectProductListByItem(item, val);

		int listCount = dtoList.size();
		for(int i=0; i<listCount; i++) {
			String proname = dtoList.get(i).getProname();
			String engproname = dtoList.get(i).getEngproname();
			String sellprice = df.format(dtoList.get(i).getSellprice())+"원";
			String detail = dtoList.get(i).getDetail();
			String imagename = dtoList.get(i).getImagename();
			String wkItem = dtoList.get(i).getItem();
			ImageIcon icon = new ImageIcon("./"+imagename);
			Image img = icon.getImage();
			Image changeImg = img.getScaledInstance(100,100, Image.SCALE_SMOOTH);
			ImageIcon changeIcon = new ImageIcon(changeImg);
			
			Object[] tmpData = {changeIcon, proname, sellprice, detail, wkItem};
			outerProductTable.addRow(tmpData);
		}
		
		// Table Column별 정렬하기.
		// Table Column(Cell) 가운데 정렬
		DefaultTableCellRenderer center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(SwingConstants.CENTER);
		
		// Table Column(Cell) 우측 정렬
		DefaultTableCellRenderer right = new DefaultTableCellRenderer();
		right.setHorizontalAlignment(SwingConstants.RIGHT);
		
		TableColumnModel tcm = innerProductTable.getColumnModel();
		
		// 특정 Column(Cell) 가운데 정렬
		tcm.getColumn(1).setCellRenderer(center);
		tcm.getColumn(2).setCellRenderer(right);
		tcm.getColumn(3).setCellRenderer(center);
		tcm.getColumn(4).setCellRenderer(center);
		
	}	// End of searchAction()
	
	private void tableClick() {
		int i = innerProductTable.getSelectedRow();
		String proname = (String) innerProductTable.getValueAt(i, 1);	// 테이블에서 상품명 가져오기.
		String item = (String) innerProductTable.getValueAt(i, 4);	// 테이블에서 상품분류 가져오기.
		tfItem.setText(item);
		tfProname.setText(proname);
	}
	private void closingAction() {
		for(int index=0; index < dtoList.size(); index++) {
			File file = new File("./" + dtoList.get(index).getImagename());
			file.delete();
		}
	}

	private void insertQTYAction() {
		String pItem = tfItem.getText().trim();
		String pProname = tfProname.getText().trim();
		int pqty = Integer.parseInt(tfQTY.getText().trim());
		
		
		StockDAO stockDAO = new StockDAO();
		boolean result = stockDAO.insertSellAction(pItem, pProname, pqty);
		
		if(result == true) {
			JOptionPane.showMessageDialog(null,  pProname + " 상품이 " + pqty + "개 등록되었습니다.");
		}else {
			JOptionPane.showMessageDialog(null, "입력중 문제가 발생했습니다.");
		}
		
	}
	
	// =========== 아래버튼 ========================
	// 현재 창을 닫고 상품관리 페이지로 이동.
	private void gotoProductList(){
		this.setVisible(false);
		StockList productList = new StockList();
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
