import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;

public class SpaceOutPostPanel extends JPanel {
    private static final String INVENTORY_PANEL_STRING = "INVENTORY_PANEL";

    private static final String MEDICAL_SUPPLIES_PANEL_STRING = "MEDICAL_SUPPLIES_PANEL";

    private static final String FOODS_PANEL_STRING = "FOODS_PANEL";

    private SpaceOutPost spaceOutPost;

    private CardLayout cardLayout;

    private JPanel content;

    private JPanel inventoryPanel;

    private JPanel medicalSuppliesPanel;

    private JPanel foodsPanel;

    private JLabel lblCoins;

    private JButton inventoryBtn;

    private JButton medicalSuppliesBtn;

    private JButton foodBtn;

	SpaceOutPostPanel(SpaceOutPost spaceOutPost) {
		super();
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(new EmptyBorder(30,0,0,0));

        this.spaceOutPost = spaceOutPost;

	
		this.content = new JPanel();
        this.cardLayout = new CardLayout();
        content.setLayout(cardLayout);

		addSideBar();
		add(content);
        addInventoryPanel();
        refreshInventoryPanel();
        addMedicalSuppliesPanel();
        addFoodsPanel();
	
        cardLayout.show(content, INVENTORY_PANEL_STRING);

        inventoryBtn.setEnabled(false);
        addActionListeners();
	}

    public void showInventoryCard() {
        cardLayout.show(content, INVENTORY_PANEL_STRING);
        refreshInventoryPanel();
    }

    public void refreshCoinLabel() {
        this.lblCoins.setText("Current coins: " + spaceOutPost.getCurrentMoney());
    }

    public void addSideBar() {
        JPanel sideBar = new JPanel();
        sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));
        
        JPanel sideBarGrid = new JPanel(new GridLayout(4,1));
        sideBarGrid.setBorder(new EmptyBorder(0,10,0,0));
        sideBar.add(sideBarGrid);
        
        JPanel currentCoinsPanel = new JPanel();
        sideBarGrid.add(currentCoinsPanel);
        this.lblCoins = new JLabel("Current $" + spaceOutPost.getCurrentMoney());
        lblCoins.setFont(new Font("Tahoma", Font.PLAIN, 18));
        currentCoinsPanel.add(lblCoins);
        
        JPanel inventoryBtnPanel = new JPanel();
        sideBarGrid.add(inventoryBtnPanel);
        this.inventoryBtn = new JButton("Inventory");
        inventoryBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        inventoryBtnPanel.add(inventoryBtn);
        
        JPanel medicalSuppliesBtnPanel = new JPanel();
        sideBarGrid.add(medicalSuppliesBtnPanel);
        this.medicalSuppliesBtn = new JButton("Purchase Medical Supplies");
        medicalSuppliesBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        medicalSuppliesBtnPanel.add(medicalSuppliesBtn);
        
        JPanel foodBtnPanel = new JPanel();
        sideBarGrid.add(foodBtnPanel);
        this.foodBtn = new JButton("Purchase Food Items");
        foodBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
        foodBtnPanel.add(foodBtn);

        sideBar.add(new JPanel());
        add(sideBar);
    }

    public void addActionListeners() {
    	foodBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg1) {
                refreshFoodsPanel();
                cardLayout.show(content, FOODS_PANEL_STRING);
                inventoryBtn.setEnabled(true);
                medicalSuppliesBtn.setEnabled(true);
                foodBtn.setEnabled(false);
            }
        });
        
        medicalSuppliesBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                refreshMedicalSuppliesPanel();
                cardLayout.show(content, MEDICAL_SUPPLIES_PANEL_STRING);
                inventoryBtn.setEnabled(true);
                foodBtn.setEnabled(true);
                medicalSuppliesBtn.setEnabled(false);
            }
        });
        
        inventoryBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg2) {
                refreshInventoryPanel();
                cardLayout.show(content, INVENTORY_PANEL_STRING);
                inventoryBtn.setEnabled(false);
                medicalSuppliesBtn.setEnabled(true);
                foodBtn.setEnabled(true);
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
        
        JLabel lblInventoryTitle = new JLabel("Inventory");
        lblInventoryTitle.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblInventoryTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblInventoryTitle.setBounds(10, 0, 700, 50);
        inventoryPanel.add(lblInventoryTitle);
        
        JLabel lblFoodTitle = new JLabel("Food");
        lblFoodTitle.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblFoodTitle.setBounds(100, 74, 181, 30);
        inventoryPanel.add(lblFoodTitle);
        
        int y = 124;
        for (Food food: spaceOutPost.getFoods()) {
            JLabel lblFoodImage = new JLabel("");
            lblFoodImage.setFont(new Font("Tahoma", Font.PLAIN, 16));
            lblFoodImage.setIcon(Funcs.getScaledIcon(Image.getFoodImagePath(food), 38, 38));   
            lblFoodImage.setBorder(new LineBorder(new Color(0, 0, 0)));
            lblFoodImage.setBounds(100, y, 38, 38);
            inventoryPanel.add(lblFoodImage);
            
            JLabel lblFoodType = new JLabel(food.getType() + "(" + food.getCount() + ")");
            lblFoodType.setFont(new Font("Tahoma", Font.PLAIN, 16));
            lblFoodType.setBounds(156, y, 187, 38);
            inventoryPanel.add(lblFoodType);
            
            y += 50;
        }
        
        JLabel lblMedicalSupplyTitle = new JLabel("Medical Supplies");
        lblMedicalSupplyTitle.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblMedicalSupplyTitle.setBounds(439, 73, 250, 30);
        inventoryPanel.add(lblMedicalSupplyTitle);
        
        y = 124;
        for (MedicalSupply medicalSupply: spaceOutPost.getMedicalSupplies()) {
            JLabel lblMedicalSupplyImage = new JLabel("");
            lblMedicalSupplyImage.setFont(new Font("Tahoma", Font.PLAIN, 16));
            lblMedicalSupplyImage.setIcon(Funcs.getScaledIcon(Image.getMedicalSupplyImagePath(medicalSupply), 38, 38));   
            lblMedicalSupplyImage.setBorder(new LineBorder(new Color(0, 0, 0)));
            lblMedicalSupplyImage.setBounds(439, y, 38, 38);
            inventoryPanel.add(lblMedicalSupplyImage);
            
            JLabel lblMedicalSupplyType = new JLabel(medicalSupply.getType() + "(" + medicalSupply.getCount() + ")");
            lblMedicalSupplyType.setFont(new Font("Tahoma", Font.PLAIN, 16));
            lblMedicalSupplyType.setBounds(487, y, 187, 38);
            inventoryPanel.add(lblMedicalSupplyType);
            
            y += 50;
        }
    }

    private void addMedicalSuppliesPanel() {
        this.medicalSuppliesPanel = new JPanel();
        medicalSuppliesPanel.setLayout(new GridLayout(2, 2, 0, 0));
        content.add(medicalSuppliesPanel, MEDICAL_SUPPLIES_PANEL_STRING);
    }

    private void refreshMedicalSuppliesPanel() {
        medicalSuppliesPanel.removeAll();

        for (MedicalSupply medicalSupply: spaceOutPost.getMedicalSupplies()) {
            JPanel panel = new JPanel();
            panel.setLayout(null);
            
            JLabel lblMedicalSupply = new JLabel(medicalSupply.getType() + "(" + medicalSupply.getCount() + ") $" + medicalSupply.getCost());
            lblMedicalSupply.setBounds(80, 0, 250, 30);
            lblMedicalSupply.setFont(new Font("Tahoma", Font.PLAIN, 16));
            panel.add(lblMedicalSupply);

            JLabel lblMedicalSupplyImage = new JLabel("");
            lblMedicalSupplyImage.setIcon(Funcs.getScaledIcon(Image.getMedicalSupplyImagePath(medicalSupply), 150, 150));
            lblMedicalSupplyImage.setBorder(new LineBorder(new Color(0, 0, 0)));
            lblMedicalSupplyImage.setBounds(83, 33, 150, 150);
            panel.add(lblMedicalSupplyImage);
            
            JButton purchaseBtn = new JButton("Purchase");
            purchaseBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
            purchaseBtn.setBounds(83, 194, 150, 30);
            if (!spaceOutPost.canAffordItem(medicalSupply.getCost())) {
            	purchaseBtn.setEnabled(false);
            }
            
            JLabel lblHealth = new JLabel("Gives " + medicalSupply.getHealth() + " health");
            if (medicalSupply.isSpacePlagueCure()) {
                lblHealth = new JLabel("Cures space plague");
            }
            lblHealth.setFont(new Font("Tahoma", Font.PLAIN, 14));
            lblHealth.setBounds(83, 230, 250, 15);
            panel.add(lblHealth);

            purchaseBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg5) {
                    if (spaceOutPost.canAffordItem(medicalSupply.getCost())) {
                        spaceOutPost.purchaseMedicalSupply(medicalSupply);
                        lblCoins.setText("Current coins: " + spaceOutPost.getCurrentMoney());
                        refreshMedicalSuppliesPanel();
                    }
                }
            });
            panel.add(purchaseBtn);
            medicalSuppliesPanel.add(panel);
        }
    }

    private void addFoodsPanel() {
        this.foodsPanel = new JPanel();
        foodsPanel.setLayout(new GridLayout(2, 3, 0, 0));
        content.add(foodsPanel, FOODS_PANEL_STRING);
    }

    private void refreshFoodsPanel() {
        foodsPanel.removeAll();

        for (Food food: spaceOutPost.getFoods()) {
            JPanel panel = new JPanel();
            panel.setLayout(null);
            
            JLabel lblFood = new JLabel(food.getType() + "(" + food.getCount() + ") $" + food.getCost());
            lblFood.setBounds(80, 0, 180, 30);
            lblFood.setFont(new Font("Tahoma", Font.PLAIN, 16));
            panel.add(lblFood);

            JLabel lblFoodImage = new JLabel("");
            lblFoodImage.setIcon(Funcs.getScaledIcon(Image.getFoodImagePath(food), 150, 150));
            lblFoodImage.setBorder(new LineBorder(new Color(0, 0, 0)));
            lblFoodImage.setBounds(83, 33, 150, 150);
            panel.add(lblFoodImage);
            
            JButton purchaseBtn = new JButton("Purchase");
            purchaseBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
            purchaseBtn.setBounds(83, 194, 150, 30);

            if (!spaceOutPost.canAffordItem(food.getCost())) {
            	purchaseBtn.setEnabled(false);
            }
            
            JLabel lblHunger = new JLabel("Gives " + food.getHungerLevel() + " hunger");
            lblHunger.setFont(new Font("Tahoma", Font.PLAIN, 14));
            lblHunger.setBounds(83, 230, 250, 15);
            panel.add(lblHunger);

            purchaseBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg5) {
                    if (spaceOutPost.canAffordItem(food.getCost())) {
                        spaceOutPost.purchaseFood(food);
                        lblCoins.setText("Current coins: " + spaceOutPost.getCurrentMoney());
                        refreshFoodsPanel();
                    }
                }
            });
            panel.add(purchaseBtn);
            foodsPanel.add(panel);
        }
    }
}
