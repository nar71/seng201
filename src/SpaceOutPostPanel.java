import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

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
        inventoryPanel.setLayout(null);
        content.add(inventoryPanel, INVENTORY_PANEL_STRING);
    }

    private void refreshInventoryPanel() {
        inventoryPanel.removeAll();
        
        JLabel lblInventory = new JLabel("Inventory");
        lblInventory.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblInventory.setHorizontalAlignment(SwingConstants.CENTER);
        lblInventory.setBounds(10, 11, 800, 51);
        inventoryPanel.add(lblInventory);
        
        JLabel lblPowerUps = new JLabel("Food");
        lblPowerUps.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblPowerUps.setBounds(100, 73, 181, 30);
        inventoryPanel.add(lblPowerUps);
        
        int yCoord = 124;
        for (Food powerUp: spaceOutPost.getFoods()) {
            JLabel lblPowerUpIcon = new JLabel("");
            lblPowerUpIcon.setFont(new Font("Tahoma", Font.PLAIN, 16));
            lblPowerUpIcon.setIcon(Funcs.getScaledIcon(Image.getFoodImagePath(powerUp), 38, 38));   
            lblPowerUpIcon.setBorder(new LineBorder(new Color(0, 0, 0)));
            lblPowerUpIcon.setBounds(100, yCoord, 38, 38);
            inventoryPanel.add(lblPowerUpIcon);
            
            JLabel lblPowerUpNumOwned = new JLabel(powerUp.getType() + "(" + powerUp.getCount() + ")");
            lblPowerUpNumOwned.setFont(new Font("Tahoma", Font.PLAIN, 16));
            lblPowerUpNumOwned.setBounds(156, yCoord, 187, 38);
            inventoryPanel.add(lblPowerUpNumOwned);
            
            yCoord += 50;
        }
        
        JLabel lblHealingItems = new JLabel("Medical Supplies");
        lblHealingItems.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblHealingItems.setBounds(439, 73, 250, 30);
        inventoryPanel.add(lblHealingItems);
        
        yCoord = 124;
        for (MedicalSupply healingItem: spaceOutPost.getMedicalSupplies()) {
            JLabel lblHealingItemIcon = new JLabel("");
            lblHealingItemIcon.setFont(new Font("Tahoma", Font.PLAIN, 16));
            lblHealingItemIcon.setIcon(Funcs.getScaledIcon(Image.getMedicalSupplyImagePath(healingItem), 38, 38));   
            lblHealingItemIcon.setBorder(new LineBorder(new Color(0, 0, 0)));
            lblHealingItemIcon.setBounds(439, yCoord, 38, 38);
            inventoryPanel.add(lblHealingItemIcon);
            
            JLabel lblHealingItemNumOwned = new JLabel(healingItem.getType() + "(" + healingItem.getCount() + ")");
            lblHealingItemNumOwned.setFont(new Font("Tahoma", Font.PLAIN, 16));
            lblHealingItemNumOwned.setBounds(487, yCoord, 187, 38);
            inventoryPanel.add(lblHealingItemNumOwned);
            
            yCoord += 50;
        }
    }

    private void addMedicalSuppliesPanel() {
        this.medicalSuppliesPanel = new JPanel();
        medicalSuppliesPanel.setLayout(new BoxLayout(medicalSuppliesPanel, BoxLayout.Y_AXIS));
        content.add(medicalSuppliesPanel, MEDICAL_SUPPLIES_PANEL_STRING);
    }

    private void refreshMedicalSuppliesPanel() {
        medicalSuppliesPanel.removeAll();

        JPanel medicalBoxPanel = new JPanel(new GridLayout(1, 4));
        medicalSuppliesPanel.add(medicalBoxPanel);

        for (MedicalSupply medicalSupply : spaceOutPost.getMedicalSupplies()) {
            JPanel medicalSupplyPanel = new JPanel();
            medicalSupplyPanel.setLayout(null);
            JLabel medicalSupplyLabel = new JLabel(medicalSupply.getType() + "(" + medicalSupply.getCount() + ")");
            medicalSupplyLabel.setBounds(20, 130, 150, 23);

            JLabel medicalSupplyImgLabel = new JLabel("");
            medicalSupplyImgLabel.setBounds(22, 10, 100, 100);
            medicalSupplyImgLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
            medicalSupplyImgLabel.setIcon(Funcs.getScaledIcon(Image.getMedicalSupplyImagePath(medicalSupply), 100, 100));

            JButton purchaseBtn = new JButton("Purchase");
            purchaseBtn.setBounds(20, 150, 150, 23);
            medicalSupplyPanel.add(purchaseBtn);

            medicalSupplyPanel.add(medicalSupplyLabel);
            medicalSupplyPanel.add(medicalSupplyImgLabel);
            medicalBoxPanel.add(medicalSupplyPanel);

            if (medicalSupply.getCost() > spaceOutPost.getCurrentMoney()) {
                purchaseBtn.setEnabled(false);
            }

            purchaseBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg5) {
                    if (spaceOutPost.canAffordItem(medicalSupply.getCost())) {
                        spaceOutPost.purchaseMedicalSupply(medicalSupply);
                        coinsLabel.setText("Current coins: " + spaceOutPost.getCurrentMoney());
                        refreshMedicalSuppliesPanel();
                    }
                }
            });
        }
    }

    private void addFoodsPanel() {
        this.foodsPanel = new JPanel();
        foodsPanel.setLayout(new BoxLayout(foodsPanel, BoxLayout.Y_AXIS));
        content.add(foodsPanel, FOODS_PANEL_STRING);
    }

    private void refreshFoodsPanel() {
        foodsPanel.removeAll();

        JPanel medicalBoxPanel = new JPanel(new GridLayout(2, 3));
        foodsPanel.add(medicalBoxPanel);

        for (Food food : spaceOutPost.getFoods()) {
            JPanel foodPanel = new JPanel();
            foodPanel.setLayout(null);
            JLabel foodLabel = new JLabel(food.getType() + "(" + food.getCount() + ")");
            foodLabel.setBounds(20, 130, 150, 23);

            JLabel foodImageLabel = new JLabel("");
            foodImageLabel.setBounds(22, 10, 100, 100);
            foodImageLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
            foodImageLabel.setIcon(Funcs.getScaledIcon(Image.getFoodImagePath(food), 100, 100));

            JButton purchaseBtn = new JButton("Purchase");
            purchaseBtn.setBounds(20, 150, 150, 23);
            foodPanel.add(purchaseBtn);

            foodPanel.add(foodLabel);
            foodPanel.add(foodImageLabel);
            medicalBoxPanel.add(foodPanel);

            if (food.getCost() > spaceOutPost.getCurrentMoney()) {
                purchaseBtn.setEnabled(false);
            }

            purchaseBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg5) {
                    if (spaceOutPost.canAffordItem(food.getCost())) {
                        spaceOutPost.purchaseFood(food);
                        coinsLabel.setText("Current coins: " + spaceOutPost.getCurrentMoney());
                        refreshFoodsPanel();
                    }
                }
            });
        }
    }
}
