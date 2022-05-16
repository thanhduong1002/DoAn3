package utility;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import view.JPanelBeverage;
import view.JPanelBill;
import view.JPanelCustomer;
import view.JPanelEmployee;
import view.JPanelOrder;
import view.JPanelYard;

public class ChangeColor {

	private JPanel root;
	private String kindSelected;
	private List<CategoryPanel> list;
	
	public ChangeColor(JPanel root) {
		this.root = root;
	}
	
	public void setView(JPanel jpn, JLabel jlb) {
		kindSelected = "Order";
		jpn.setBackground(new Color(148,0,211));
		jpn.setBackground(new Color(148,0,211));
		root.removeAll();
		root.setLayout(new BorderLayout());
		root.add(new JPanelOrder());
		root.validate();
		root.repaint();
	}
	public void setEvent(List<CategoryPanel> listItem) {
		this.list = listItem;
		for(CategoryPanel item : list) {
			item.getJlb().addMouseListener(new LabelEvent(item.getKind(), item.getJpn(), item.getJlb()));
		}
	}
	private class LabelEvent implements MouseListener{
		
		private JPanel node;
	    private String kind;
	    private JPanel jpnItem;
	    private JLabel jlbItem;

	    public LabelEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
	           this.kind = kind;
	           this.jpnItem = jpnItem;
	           this.jlbItem = jlbItem;
	    }
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			switch (kind) {
            case "Order":
                node = new JPanelOrder();
                break;
            case "Employee":
            	node = new JPanelEmployee();
            	break;
            case "Customer":
            	node = new JPanelCustomer();
            	break;
            case "Beverage":
            	node = new JPanelBeverage();
            	break;
            case "Bill":
            	node = new JPanelBill();
            	break;
            case "Yard":
            	node = new JPanelYard();
            	break;
            default:
                break;
			}
			root.removeAll();
			root.setLayout(new BorderLayout());
			root.add(node);
			root.validate();
			root.repaint();
			setChangeBackground(kind);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			kindSelected = kind;
	        jpnItem.setBackground(new Color(148,0,211));
	        jlbItem.setBackground(new Color(148,0,211));
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			jpnItem.setBackground(new Color(148,0,211));
	        jlbItem.setBackground(new Color(148,0,211));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			if (!kindSelected.equalsIgnoreCase(kind)) {
                jpnItem.setBackground(new Color(50, 205, 50));
                jlbItem.setBackground(new Color(50, 205, 50));
			}
		}
		private void setChangeBackground(String kind) {
			for(CategoryPanel item : list) {
				if(item.getKind().equalsIgnoreCase(kind)) {
					item.getJlb().setBackground(new Color(148,0,211));
					item.getJpn().setBackground(new Color(148,0,211));
				}else {
					item.getJlb().setBackground(new Color(50, 205, 50));
					item.getJpn().setBackground(new Color(50, 205, 50));
				}	
			}
		}
	}
}
