import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SpaceOutPostPanel extends JPanel {
    private static final String INVENTORY_PANEL_STRING = "INVENTORY_PANEL";

    private static final String MEDICAL_SUPPLIES_PANEL_STRING = "MEDICAL_SUPPLIES_PANEL";

    private static final String FOODS_PANEL_STRING = "FOODS_PANEL";

    private SpaceOutPost spaceOutPost;

    private CardLayout cardLayout;

    private JPanel content;

    private JPanel sideBar;

    private JPanel inventoryPanel;

    private JPanel medicalSuppliesPanel;

    private JPanel foodsPanel;

    private JLabel coinsLabel;

    private JButton inventoryBtn;

    private JButton medicalSuppliesBtn;

    private JButton foodBtns;

	SpaceOutPostPanel(SpaceOutPost spaceOutPost) {
		super();

        this.spaceOutPost = spaceOutPost;

		JPanel sideBar = new JPanel();
		this.content = new JPanel();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(sideBar);
		add(content);
		
		this.cardLayout = new CardLayout();
		content.setLayout(cardLayout);
        sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));
		
        JPanel panel = new JPanel();
        sideBar.add(panel);
        
        this.coinsLabel = new JLabel("Current coins: " + spaceOutPost.getCurrentMoney());
        panel.add(coinsLabel);
        
        JPanel panel_1 = new JPanel();
        sideBar.add(panel_1);
        
        // SideBar
        this.inventoryBtn = new JButton("Inventory");
        panel_1.add(inventoryBtn);
        
        JPanel panel_2 = new JPanel();
        sideBar.add(panel_2);
        this.medicalSuppliesBtn = new JButton("Medical Supplies");
        panel_2.add(medicalSuppliesBtn);
        
        JPanel panel_3 = new JPanel();
        sideBar.add(panel_3);
        this.foodBtns = new JButton("Foods");
        panel_3.add(foodBtns);

		// Inventory
        addInventoryPanel();
        refreshInventoryPanel();
		
		// Medical Supplies
        addMedicalSuppliesPanel();
		
		// Foods
        addFoodsPanel();
	
        // SHOW inventory By default
        cardLayout.show(content, INVENTORY_PANEL_STRING);

        // Action handlers
        inventoryBtn.setEnabled(false);

        addActionListeners();
	}

    public void refresh() {
        cardLayout.show(content, INVENTORY_PANEL_STRING);
        refreshCoinLabel();
        refreshInventoryPanel();
        refreshMedicalSuppliesPanel();
        refreshFoodsPanel();
        inventoryBtn.setEnabled(false);
        medicalSuppliesBtn.setEnabled(true);
        foodBtns.setEnabled(true);
    }

    public void refreshCoinLabel() {
        this.coinsLabel.setText("Current coins: " + spaceOutPost.getCurrentMoney());
    }

    public void addActionListeners() {
        foodBtns.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg1) {
                refreshFoodsPanel();
                cardLayout.show(content, FOODS_PANEL_STRING);
                inventoryBtn.setEnabled(true);
                medicalSuppliesBtn.setEnabled(true);
                foodBtns.setEnabled(false);
            }
        });
        
        medicalSuppliesBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                refreshMedicalSuppliesPanel();
                cardLayout.show(content, MEDICAL_SUPPLIES_PANEL_STRING);
                inventoryBtn.setEnabled(true);
                foodBtns.setEnabled(true);
                medicalSuppliesBtn.setEnabled(false);
            }
        });
        
        inventoryBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg2) {
                refreshInventoryPanel();
                cardLayout.show(content, INVENTORY_PANEL_STRING);
                inventoryBtn.setEnabled(false);
                medicalSuppliesBtn.setEnabled(true);
                foodBtns.setEnabled(true);
            }
        });
    }

    private void addInventoryPanel() {
        this.inventoryPanel = new JPanel();
        content.add(inventoryPanel, INVENTORY_PANEL_STRING);
    }

    private void refreshInventoryPanel() {
        inventoryPanel.removeAll();

        JPanel medPanelMain = new JPanel();
        medPanelMain.setLayout(new BoxLayout(medPanelMain, BoxLayout.Y_AXIS));
        for (MedicalSupply m : spaceOutPost.getMedicalSupplies()) {
            JPanel medPanel = new JPanel();
            JLabel medLabel = new JLabel(m.getType() + "(" + m.getCount() + ")");
            medPanel.add(medLabel);
            medPanelMain.add(medPanel);
        }
        inventoryPanel.add(medPanelMain);

        JPanel foodPanelMain = new JPanel();
        foodPanelMain.setLayout(new BoxLayout(foodPanelMain, BoxLayout.Y_AXIS));
        for (Food f : spaceOutPost.getFoods()) {
            JPanel foodPanel = new JPanel();
            JLabel foodLabel = new JLabel(f.getType() + "(" + f.getCount() + ")");
            foodPanel.add(foodLabel);
            foodPanelMain.add(foodPanel);
        }
        inventoryPanel.add(foodPanelMain);
    }

    private void addMedicalSuppliesPanel() {
        this.medicalSuppliesPanel = new JPanel();
        content.add(medicalSuppliesPanel, MEDICAL_SUPPLIES_PANEL_STRING);
    }

    private void refreshMedicalSuppliesPanel() {
        medicalSuppliesPanel.removeAll();

        JPanel medicalSupplyBox = new JPanel();
        medicalSupplyBox.setLayout(new BoxLayout(medicalSupplyBox, BoxLayout.Y_AXIS));
        for (MedicalSupply m: spaceOutPost.getMedicalSupplies()) {
            JPanel medicalSupplyViewPanel = new JPanel();
            medicalSupplyViewPanel.add(new JLabel(m.getType() + " ($" + m.getCost() + ")"));

            JButton purchaseBtn = new JButton("Purchase");
            medicalSupplyViewPanel.add(purchaseBtn);
            
            medicalSupplyBox.add(medicalSupplyViewPanel);

            if (m.getCost() > spaceOutPost.getCurrentMoney()) {
                purchaseBtn.setEnabled(false);
            }

            purchaseBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg5) {
                    if (spaceOutPost.canAffordItem(m.getCost())) {
                        spaceOutPost.purchaseMedicalSupply(m);
                        coinsLabel.setText("Current coins: " + spaceOutPost.getCurrentMoney());
                        refreshMedicalSuppliesPanel();
                    }
                }
            });
        }
        medicalSuppliesPanel.add(medicalSupplyBox);
    }

    private void addFoodsPanel() {
        this.foodsPanel = new JPanel();
        content.add(foodsPanel, FOODS_PANEL_STRING);
    }

    private void refreshFoodsPanel() {
        foodsPanel.removeAll();

        JPanel foodBox = new JPanel();
        foodBox.setLayout(new BoxLayout(foodBox, BoxLayout.Y_AXIS));
        for (Food fs: spaceOutPost.getFoods()) {
            JPanel foodViewPanel = new JPanel();
            foodViewPanel.add(new JLabel(fs.getType() + " ($" + fs.getCost() + ")"));
            JButton purchaseBtnF = new JButton("Purchase");
            foodViewPanel.add(purchaseBtnF);
            foodBox.add(foodViewPanel);

            if (fs.getCost() > spaceOutPost.getCurrentMoney()) {
                purchaseBtnF.setEnabled(false);
            }

            purchaseBtnF.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg5) {
                    if (spaceOutPost.canAffordItem(fs.getCost())) {
                        spaceOutPost.purchaseFood(fs);
                        coinsLabel.setText("Current coins: " + spaceOutPost.getCurrentMoney());
                        refreshFoodsPanel();
                    }
                }
            });
        }
        foodsPanel.add(foodBox);
    }
}
