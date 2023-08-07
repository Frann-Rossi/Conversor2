import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MiConversor {

	private JFrame frame;
	private JButton btn;
	private JComboBox<Moneda> cmb;
	private JLabel lbl;
	private JTextField txt; 
	
	public enum Moneda{
		pesos_dolar,
		pesos_euro,
		pesos_libra,
		dolar_pesos,
		euro_pesos,
		libra_pesos
	}
	
	public double dolar = 380.15;
	public double euro = 390.19;
	public double libra = 270.89;
	
	public double valorInput = 0.00;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MiConversor window = new MiConversor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MiConversor() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txt = new JTextField();
		txt.setBounds(12, 12, 132, 19);
		frame.getContentPane().add(txt);
		txt.setColumns(10);
		
		cmb = new JComboBox<Moneda>();
		cmb.setModel(new DefaultComboBoxModel<>(Moneda.values()));
		cmb.setBounds(12, 43, 132, 24);
		frame.getContentPane().add(cmb);
		
		btn = new JButton("Convertir");
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Convertir();
			}
		});
		btn.setBounds(190, 43, 117, 25);
		frame.getContentPane().add(btn);
		
		lbl = new JLabel("00.00");
		lbl.setBounds(190, 14, 117, 15);
		frame.getContentPane().add(lbl);
	}
	
	public void Convertir() {
		if(Validar(txt.getText())) {
			Moneda moneda = (Moneda)cmb.getSelectedItem();
			switch(moneda) {
			case pesos_dolar:
				PesosAMoneda(dolar);
				break;
			case pesos_euro:
				PesosAMoneda(euro);
				break;
			case pesos_libra:
				PesosAMoneda(libra);
				break;
			case dolar_pesos:
				MonedaAPesos(dolar);
				break;
			case euro_pesos:
				MonedaAPesos(euro);
				break;
			case libra_pesos:
				MonedaAPesos(libra); 
				break;
			}
		}
	}
	
	public void PesosAMoneda(double moneda) {
		double res = valorInput / moneda; 	
		lbl.setText(Redondear(res));
	}
	
	public void MonedaAPesos(double moneda) {
		double res = valorInput * moneda;
		lbl.setText(Redondear(res));
	}
	
	public String Redondear(double valor) {
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.HALF_UP);
		return df.format(valor);
	}
	
	public boolean Validar(String texto) {
		try {
			double x = Double.parseDouble(texto);
			if(x > 0) { 
				valorInput = x;
				return true;
			} else {
				lbl.setText("¡Ingrese un número positivo!");
				return false;
			}
		} catch(NumberFormatException e) {
			lbl.setText("¡Solamente números!");
			return false;
		}
	}
}