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

import com.javalec.function.AdminDAO;
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

public class ProductList extends JFrame {

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
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductList frame = new ProductList();
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
	public ProductList() {
		addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowActivated(WindowEvent e) {
//				getComboCategory();
//				tableProductInit();
//				searchProductAction();
//			}
			@Override
			public void windowOpened(WindowEvent e) {
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
			tabbedPane.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					int selectedIndex = tabbedPane.getSelectedIndex();
					switch(selectedIndex) {
					case 0 : // 상품관리 tab
//						getComboCategory();
						tableProductInit();
						searchProductAction(); 
						break;
					case 1 : // 재고관리 tab
//						tableStockInit();
//						searchStockAction(); 
						break;
					case 2 : // 매출관리 tab
//						tableStockInit();
//						searchStockAction(); 
						break;	
					case 3 : // 회원관리 tab
//						tableMemberInit();
//						searchMemberAction(); 
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
			tabProduct.add(getLblNewLabel());
			tabProduct.add(getLblNewLabel_1());
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
			scrollPane.setBounds(40, 57, 871, 331);
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
	} // 여기까지 상품관리 화면.
	
	// [상품관리] ===== Function ========================
	private void tableProductInit() { //			proname, sellprice, detail, nutritional, ingredient, image, imagename, wItem
		// Table Column 명 정하기.
		outerProductTable.addColumn("이미지");
		outerProductTable.addColumn("상품명");
		outerProductTable.addColumn("상품가격");
		outerProductTable.addColumn("설명");
//		outerProductTable.addColumn("영양정보");
//		outerProductTable.addColumn("성분");
//		outerProductTable.addColumn("이미지이름");
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
		
//		// 영양정보
//		colNo = 4;
//		col = innerProductTable.getColumnModel().getColumn(colNo);
//		width = 50;
//		col.setPreferredWidth(width);
//		
//		// 성분
//		colNo = 5;
//		col = innerProductTable.getColumnModel().getColumn(colNo);
//		width = 80;
//		col.setPreferredWidth(width);
		
//		// 이미지
//		colNo = 7;
//		col = innerProductTable.getColumnModel().getColumn(colNo);
//		width = 150;
//		col.setPreferredWidth(width);
		
		// 테이블 내용 지우기.
		int i = innerProductTable.getRowCount();
		for(int j=0; j<i; j++) {
			outerProductTable.removeRow(0);
		}
		
		innerProductTable.setAutoResizeMode(innerProductTable.AUTO_RESIZE_OFF);
		
	}
	// 카테고리 가져오기.
	private void getComboCategory() {
		AdminDAO adminDAO = new AdminDAO();
		ArrayList<String> listCategory = adminDAO.selectItem();
//		String[] strCategory = new listCategory.get(0);
		
		for(int i=0; i<listCategory.size(); i++) {
			cbProduct.addItem(listCategory.get(i));
		}
	}
	// 상품관리 테이블 불러오기.
	private void searchProductAction() {
		String item ="";
//		String item = cbProduct.getSelectedItem();
		System.out.println(cbProduct.getSelectedItem());		
		String val = tfProduct.getText();
		
		AdminDAO dao = new AdminDAO();
		dtoList = dao.selectProductListByItem(item, val);

		int listCount = dtoList.size();
		for(int i=0; i<listCount; i++) {
//			proname, sellprice, detail, nutritional, ingredient, image, imagename, wItem
			String proname = dtoList.get(i).getProname();
			String sellprice = df.format(dtoList.get(i).getSellprice())+"원";
			String detail = dtoList.get(i).getDetail();
//			String nutritional = dtoList.get(i).getNutritional();
//			String ingredient = dtoList.get(i).getIngredient();
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
	private void closingAction() {
		for(int index=0; index < dtoList.size(); index++) {
			File file = new File("./" + dtoList.get(index).getImagename());
			file.delete();
		}
	}
	
	private void addProduct() {
		this.setVisible(false);
		ProductList admin = new ProductList();
		admin.main(null);
	}
//	private void homeScreen() {
//		this.frame.setVisible(false); // 현재화면 끄고
//		Main window = new Main();
//		window.frame.setVisible(true); // 홈 화면 키기
//	}
//	private void homeScreen() {
//		// 로그인 성공시
//		ShareVar.loginID = id1;
//		frame.setVisible(false);
//		Product_Search ps = new Product_Search();
//		ps.main(null);
//	}
	// Table에서 Row를 click했을 경우
	private void tableClick() {
		int i = innerProductTable.getSelectedRow();
		String tkSequence = (String) innerProductTable.getValueAt(i, 0);
		int wkSequence = Integer.parseInt(tkSequence);
		
//		frame.setVisible(false);
//		Product_Search ps = new Product_Search();
//		ps.main(null);
//			Dao dao = new Dao(wkSequence);
//			Dto dto = dao.tableClick();
//			
//			tfSeqNo.setText(Integer.toString(dto.getSeqno()));
//			tfName.setText(dto.getName());
//			tfTelno.setText(dto.getTelno());
//			tfAddress.setText(dto.getAddress());
//			tfEmail.setText(dto.getEmail());
//			tfRelation.setText(dto.getRelation());
	}
}