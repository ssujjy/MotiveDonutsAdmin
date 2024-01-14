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
import com.javalec.function.MemberDAO;
import com.javalec.function.ProductDAO;
import com.javalec.model.MemberDTO;
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
import javax.swing.DefaultComboBoxModel;

public class MemberList extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel tabProduct;
	private final DefaultTableModel outerMemberTable = new DefaultTableModel();
	private JTextField tfMember;
	private JButton btnSearchMember;
	private JScrollPane scrollPane;
	private JTable innerMemberTable;
	private JButton btnItem;
	private JButton btnStock;
	private JButton btnMember;
	private JButton btnPurchase;
	private JButton btnProductList;
	private JComboBox cbMember;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemberList frame = new MemberList();
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
	public MemberList() {
		setTitle("회원목록");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				tableMemberInit();
				searchMemberAction();
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
			tabbedPane.addTab("회원목록", null, getTabProduct(), null);
		}
		return tabbedPane;
	}
	private JPanel getTabProduct() {
		if (tabProduct == null) {
			tabProduct = new JPanel();
			tabProduct.setLayout(null);
			tabProduct.add(getTfMember());
			tabProduct.add(getBtnSearchMember());
			tabProduct.add(getScrollPane());
			tabProduct.add(getBtnItem());
			tabProduct.add(getBtnStock());
			tabProduct.add(getBtnMember());
			tabProduct.add(getBtnPurchase());
			tabProduct.add(getBtnProductList());
			tabProduct.add(getCbMember());
		}
		return tabProduct;
	}
	// 여기까지 기본에서 Tab까지 구현한 화면 
	
	private JTextField getTfMember() {
		if (tfMember == null) {
			tfMember = new JTextField();
			tfMember.setBounds(198, 20, 239, 21);
			tfMember.setColumns(10);
		}
		return tfMember;
	}
	private JButton getBtnSearchMember() {
		if (btnSearchMember == null) {
			btnSearchMember = new JButton("검색");
			btnSearchMember.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tableMemberInit();
					searchMemberAction(); 
				}
			});
			btnSearchMember.setBounds(449, 19, 97, 23);
		}
		return btnSearchMember;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(40, 57, 871, 331);
			scrollPane.setViewportView(getInnerMemberTable());
		}
		return scrollPane;
	}
	private JTable getInnerMemberTable() {
		if (innerMemberTable == null) {
			innerMemberTable = new JTable(){ 								
				public Class getColumnClass(int column) { 				
			        return (column == 1) ? Icon.class : Object.class; 	// 이미지가 나올 컬럼에 맞는 번호로 적어줘야 함.
			      }
			};
			innerMemberTable.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
				}
			});
			innerMemberTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			innerMemberTable.setRowHeight(150); 	
			innerMemberTable.setModel(outerMemberTable);
			
		}
		return innerMemberTable;
	}
	private JComboBox getCbMember() {
		if (cbMember == null) {
			cbMember = new JComboBox();
			cbMember.setModel(new DefaultComboBoxModel(new String[] {"전체", "회원명", "회원ID", "전화번호"}));
			cbMember.setBounds(40, 19, 146, 23);
		}
		return cbMember;
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
	
	// [상품관리] ===== Function ========================
	private void tableMemberInit() { //image, custid, custpw, custname, phone, joinactive, deactive
		// Table Column 명 정하기.
		outerMemberTable.addColumn("No.");
		outerMemberTable.addColumn("회원사진");
		outerMemberTable.addColumn("회원명");
		outerMemberTable.addColumn("회원ID");
		outerMemberTable.addColumn("회원PW");
		outerMemberTable.addColumn("전화번호");
		outerMemberTable.addColumn("가입일");
		outerMemberTable.addColumn("탈퇴일");
		outerMemberTable.setColumnCount(8);
		// No.
		int colNo = 0;
		TableColumn col = innerMemberTable.getColumnModel().getColumn(colNo);
		int width = 50;
		col.setPreferredWidth(width);
		
		// 회원사진
		colNo = 1;
		col = innerMemberTable.getColumnModel().getColumn(colNo);
		width = 150;
		col.setPreferredWidth(width);
		
		// 회원명
		colNo = 2;
		col = innerMemberTable.getColumnModel().getColumn(colNo);
		width = 150;
		col.setPreferredWidth(width);
		
		// 회원ID
		colNo = 3;
		col = innerMemberTable.getColumnModel().getColumn(colNo);
		width = 80;
		col.setPreferredWidth(width);
		
		// 회원PW
		colNo = 4;
		col = innerMemberTable.getColumnModel().getColumn(colNo);
		width = 80;
		col.setPreferredWidth(width);

		// 전화번호
		colNo = 5;
		col = innerMemberTable.getColumnModel().getColumn(colNo);
		width = 150;
		col.setPreferredWidth(width);
		
		// 가입일
		colNo = 6;
		col = innerMemberTable.getColumnModel().getColumn(colNo);
		width = 150;
		col.setPreferredWidth(width);
		
		// 탈퇴일
		colNo = 7;
		col = innerMemberTable.getColumnModel().getColumn(colNo);
		width = 150;
		col.setPreferredWidth(width);
		
		// 테이블 내용 지우기.
		int i = innerMemberTable.getRowCount();
		for(int j=0; j<i; j++) {
			outerMemberTable.removeRow(0);
		}
		
		innerMemberTable.setAutoResizeMode(innerMemberTable.AUTO_RESIZE_OFF);
		
	}

	// 상품관리 테이블 불러오기.
	private void searchMemberAction() {
		String item = (String) cbMember.getSelectedItem();
		String val = tfMember.getText();
		
		MemberDAO memberDAO = new MemberDAO();
		ArrayList<MemberDTO> dtoList = memberDAO.searchMemberAction(item, val);

		int listCount = dtoList.size();
		for(int i=0; i<listCount; i++) {	//custid, custpw, custname, phone, joinactive, deactive
			String custid = dtoList.get(i).getCustid();
			String custpw = dtoList.get(i).getCustpw();
			String custname = dtoList.get(i).getCustname();
			String phone = dtoList.get(i).getPhone();
			String joinactive = dtoList.get(i).getJoinactive();
			String deactive = dtoList.get(i).getDeactive();
			ImageIcon icon = new ImageIcon("./"+custid);
			Image img = icon.getImage();
			Image changeImg = img.getScaledInstance(100,100, Image.SCALE_SMOOTH);
			ImageIcon changeIcon = new ImageIcon(changeImg);
			int listNum = i+1;
			Object[] tmpData = {listNum, changeIcon, custname, custid, custpw, phone, joinactive, deactive};
			outerMemberTable.addRow(tmpData);
		}
		// Table Column별 정렬하기.
		// Table Column(Cell) 가운데 정렬
		DefaultTableCellRenderer center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(SwingConstants.CENTER);
		
		TableColumnModel tcm = innerMemberTable.getColumnModel();
		
		// 특정 Column(Cell) 가운데 정렬	<< 이미지 컬럼은 빼줌.
		tcm.getColumn(0).setCellRenderer(center);
		tcm.getColumn(2).setCellRenderer(center);
		tcm.getColumn(3).setCellRenderer(center);
		tcm.getColumn(4).setCellRenderer(center);
		tcm.getColumn(5).setCellRenderer(center);
		tcm.getColumn(6).setCellRenderer(center);
		tcm.getColumn(7).setCellRenderer(center);
	}	// End of searchAction()
	
	// =========== 아래버튼 ========================
	// 현재 창을 닫고 상품관리 페이지로 이동.
	private void gotoProductList(){
		this.setVisible(false);
		MemberList productList = new MemberList();
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
//	private void tableClick() {
//		int i = innerMemberTable.getSelectedRow();
//		ShareVar.proname = (String) innerMemberTable.getValueAt(i, 1);	// 테이블에서 상품명 가져오기.
//	}

}
