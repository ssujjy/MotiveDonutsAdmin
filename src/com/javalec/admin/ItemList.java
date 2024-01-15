package com.javalec.admin;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.javalec.function.ProductDAO;
import com.javalec.model.ProductDTO;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.Icon;
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
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ItemList extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel tabSales;
	private JPanel tabItem;
	private JPanel tabMember;
	private final DefaultTableModel outerItemTable = new DefaultTableModel();
	private JPanel tabStock;
	private JScrollPane scrollPane;
	private JTable innerItemTable;
	private ArrayList<ProductDTO> dtoList = null;
	private JLabel lblNewLabel;
	private JButton btnItem;
	private JButton btnStock;
	private JButton btnMember;
	private JButton btnPurchase;
	private JTextField tfItem;
	private JRadioButton rbNewItem;
	private JRadioButton rbDeleteItem;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnOK;
	private JButton btnProductList;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ItemList frame = new ItemList();
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
	public ItemList() {
		setTitle("상품분류");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				tableItemInit();
				searchItemAction();
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
			tabbedPane.addTab("상품분류", null, getTabItem(), null);
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
	private JPanel getTabItem() {
		if (tabItem == null) {
			tabItem = new JPanel();
			tabItem.setLayout(null);
			tabItem.add(getScrollPane());
			tabItem.add(getLblNewLabel());
			tabItem.add(getBtnItem());
			tabItem.add(getBtnStock());
			tabItem.add(getBtnMember());
			tabItem.add(getBtnPurchase());
			tabItem.add(getTfItem());
			tabItem.add(getRbNewItem());
			tabItem.add(getRbDeleteItem());
			tabItem.add(getBtnOK());
			tabItem.add(getBtnProductList_1());
		}
		return tabItem;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(12, 10, 427, 374);
			scrollPane.setViewportView(getInnerItemTable());
		}
		return scrollPane;
	}
	private JTable getInnerItemTable() {
		if (innerItemTable == null) {
			innerItemTable = new JTable(){ 								
				public Class getColumnClass(int column) { 				
			        return (column == 0) ? Icon.class : Object.class; 	
			      }
			};
			innerItemTable.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					tableClick();
				}
			});
			innerItemTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//			innerItemTable.setRowHeight(150); 	
			innerItemTable.setModel(outerItemTable);
			
		}
		return innerItemTable;
	}
	private JRadioButton getRbNewItem() {
		if (rbNewItem == null) {
			rbNewItem = new JRadioButton("등록");
			rbNewItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					screenPartition();
				}
			});
			buttonGroup.add(rbNewItem);
			rbNewItem.setBounds(481, 24, 61, 23);
		}
		return rbNewItem;
	}
	private JRadioButton getRbDeleteItem() {
		if (rbDeleteItem == null) {
			rbDeleteItem = new JRadioButton("삭제");
			rbDeleteItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					screenPartition();
				}
			});
			buttonGroup.add(rbDeleteItem);
			rbDeleteItem.setBounds(546, 24, 61, 23);
		}
		return rbDeleteItem;
	}
	// -------------- 라디오버튼 그룹 ---------------- //
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("상품종류 : ");
			lblNewLabel.setBounds(481, 70, 69, 15);
		}
		return lblNewLabel;
	}

	private JTextField getTfItem() {
		if (tfItem == null) {
			tfItem = new JTextField();
			tfItem.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						tableItemInit();
						searchItemAction();
						screenPartition();
					}
					
				}
			});
			tfItem.setBounds(550, 67, 242, 21);
			tfItem.setColumns(10);
		}
		return tfItem;
	}

	private JButton getBtnOK() {
		if (btnOK == null) {
			btnOK = new JButton("OK");
			btnOK.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					okAction();
				}
			});
			btnOK.setBounds(811, 66, 97, 23);
		}
		return btnOK;
	}
	//---------------- 아래 버튼 ----------------------------------//
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
	//---------------- 아래 버튼 ----------------------------------//
	
	// [상품목록] ===== Function ========================
	private void tableItemInit() { 
		// Table Column 명 정하기.
		outerItemTable.addColumn("상품분류");
		outerItemTable.setColumnCount(1);
		// 상품분류
		int colNo = 0;
		TableColumn col = innerItemTable.getColumnModel().getColumn(colNo);
		int width = 150;
		col.setPreferredWidth(width);
		
		// 테이블 내용 지우기.
		int i = innerItemTable.getRowCount();
		for(int j=0; j<i; j++) {
			outerItemTable.removeRow(0);
		}
		innerItemTable.setAutoResizeMode(innerItemTable.AUTO_RESIZE_OFF);
	}

	// 테이블에서 상품분류 불러오기.
	private void searchItemAction() {		
		ProductDAO prodcutDAO = new ProductDAO();
		ArrayList<String> listCategory = prodcutDAO.selectItem();

		for(int i=0; i<listCategory.size(); i++) {
			Object[] tmpData = {listCategory.get(i)};
			outerItemTable.addRow(tmpData);
		}
		
		// Table Column(Cell) 가운데 정렬
		DefaultTableCellRenderer center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(SwingConstants.CENTER);
		
		TableColumnModel tcm = innerItemTable.getColumnModel();
		
		// 특정 Column(Cell) 가운데 정렬
		tcm.getColumn(0).setCellRenderer(center);
		
	}	// End of searchAction()

	private void tableClick() {
		int row = innerItemTable.getSelectedRow();
		String item = (String) innerItemTable.getValueAt(row, 0);	// 테이블에서 상품분류 가져오기.
		tfItem.setText(item);
	}
	
	// Edit 결정
	private void screenPartition() {
		// 입력일 경우
		if(rbNewItem.isSelected() == true) {
			btnOK.setVisible(true);
			tfItem.setEditable(true);
		}
		
		// 삭제일 경우
		if(rbDeleteItem.isSelected() == true) {
			btnOK.setVisible(true);
			tfItem.setEditable(false);
		}
	}
	private void okAction() {
		String item = tfItem.getText().trim();
		// 입력일 경우
		if(rbNewItem.isSelected() == true) {
			int i_chk = insertFieldCheck();
			if(i_chk == 0) {
				insertItemAction(item);
			}else {
				JOptionPane.showMessageDialog(null, "데이터를 확인하세요");
			}
			tableItemInit();
			searchItemAction();
			clearColumn();
			screenPartition();
		}

		// 삭제일 경우
		if(rbDeleteItem.isSelected() == true) {
			int i_chk = insertFieldCheck();
			if(i_chk == 0) {
				deleteItemAction(item);
			}else {
				JOptionPane.showMessageDialog(null, "데이터를 확인하세요");
			}
			tableItemInit();
			searchItemAction();
			clearColumn();
			screenPartition();
		}
	}
	// 필드체크
	private int insertFieldCheck() {
		int i = 0;
		
		if(tfItem.getText().trim().length() == 0) {
			i++;
			tfItem.requestFocus();
		}
		
		return i;
	}
	private void clearColumn() {
		tfItem.setText("");
	}
	
	private void insertItemAction(String item) {
		ProductDAO dao = new ProductDAO();
		boolean chkResult = dao.checkItemAction(item);
		if(chkResult == true) {
			JOptionPane.showMessageDialog(null,  " 이미 존재하는 상품 분류입니다. ");
		}else {
			boolean result = dao.insertItemAction(item);
			if(result == true) {
				JOptionPane.showMessageDialog(null,  item + "이 추가 되었습니다.");
			}else {
				JOptionPane.showMessageDialog(null, "입력중 문제가 발생했습니다.");
			}
		}
	}
	
	private void deleteItemAction(String item) {
		ProductDAO dao = new ProductDAO();
		boolean chk = dao.chkExistRegisterAction(item);
		if(chk == true) {
			JOptionPane.showMessageDialog(null,  " 상품이 등록된 상품분류는 삭제할 수 없습니다. ");
		}else {
			boolean result = dao.deleteItemAction(item);
			if(result == true) {
				JOptionPane.showMessageDialog(null,  item + "이 삭제 되었습니다.");
			}else {
				JOptionPane.showMessageDialog(null, "삭제중 문제가 발생했습니다.");
			}
		}
	}
	
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
	private JButton getBtnProductList_1() {
		if (btnProductList == null) {
			btnProductList = new JButton("상품관리");
			btnProductList.setBounds(12, 417, 146, 23);
		}
		return btnProductList;
	}
}
