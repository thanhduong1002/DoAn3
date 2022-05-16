package view;

import javax.swing.JPanel;

public class JPanelStatistic extends JPanel {

	/**
	 * Create the panel.
	 */
	public JPanelStatistic() {
		setLayout(null);
		
		JPanel panelDoanhThu = new JPanel();
		panelDoanhThu.setBounds(28, 33, 780, 164);
		add(panelDoanhThu);
		
		JPanel panelDatSan = new JPanel();
		panelDatSan.setBounds(28, 243, 780, 164);
		add(panelDatSan);

	}
}
