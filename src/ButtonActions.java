import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class ButtonActions implements ActionListener {
	private UIState uiState;
	private Game game;
	private boolean refresh;
	private JPanel panel;
	public ButtonActions(UIState uiState, int i, JPanel panel){
		this.uiState = uiState;
		this.panel=panel;
		game=uiState.game;
		if(i==0){
			refresh=true;
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if(refresh){
			game.getPlayer(game.getCurrentPlayerIndex()).refreshHand();
		}else{
			game.getPlayer(game.getCurrentPlayerIndex()).swapTiles();
		}
		panel.getComponent(5).setEnabled(false);
		panel.getComponent(4).setEnabled(false);
		panel.repaint();
		
	}

}
