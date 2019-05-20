        private void refreshPlanetsOntoPanel(ButtonGroup buttonGroup, JPanel planetsPanel) {
        planetsPanel.removeAll();
        Planet planet = environment.getPlanet();
        Planet[] allPlanets = planet.getAll();
        for (int i = 0; i < allPlanets.length; i++) {
            if (!allPlanets[i].getName().equals(environment.getCurrentPlanet().getName())) {
                fillPlanetPanelAndButtonGroup(allPlanets[i], buttonGroup, planetsPanel);
            }
        }
    }

    private void fillMemberPanelAndButtonGroup(CrewMember member, ButtonGroup buttonGroup, JPanel panel) {
        JRadioButton memberRadio = new JRadioButton(member.getName());
        memberRadio.putClientProperty("CrewMember", member);
        if (!member.hasActionsLeft()) {
            memberRadio.setEnabled(false);
        }

        JLabel memberImageLabel = new JLabel("");
        memberImageLabel.setBorder(new LineBorder(new Color(0, 0, 0)));

        // Rescale what we already have
        ImageIcon smallerImg = getScaledMemberIcon(member);
        memberImageLabel.setIcon(smallerImg);

        buttonGroup.add(memberRadio);
        panel.add(memberRadio);
        panel.add(memberImageLabel);
    }

    private JPanel getCrewSelectPanel(ButtonGroup buttonGroup) {
        JPanel memberPanel = new JPanel();
        for (CrewMember member: crew.getMembers()) {
            fillMemberPanelAndButtonGroup(member, buttonGroup, memberPanel);
        }

        return memberPanel;
    }

    private void refreshCrewOntoPanel(ButtonGroup buttonGroup, JPanel panel) {
        panel.removeAll();
        for (CrewMember member: crew.getMembers()) {
            fillMemberPanelAndButtonGroup(member, buttonGroup, panel);
        }
    }
    
    private void fillPlanetPanelAndButtonGroup(Planet planet, ButtonGroup buttonGroup, JPanel panel) {
        JLabel planetImageLabel = new JLabel("");
        planetImageLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
        planetImageLabel.setIcon(new ImageIcon(Image.getPlanetImagePath(planet)));
        JRadioButton planetRadioBtn = new JRadioButton(planet.getName() + "    ");
        planetRadioBtn.putClientProperty("Planet", planet);
        buttonGroup.add(planetRadioBtn);
        panel.add(planetRadioBtn);
        panel.add(planetImageLabel);
    }

    private JPanel getSelectPlanetPanel(ButtonGroup buttonGroup) {
        Planet planet = environment.getPlanet();
        Planet[] allPlanets = planet.getAll();

        JPanel planetsPanel = new JPanel();
        for (int i = 0; i < allPlanets.length; i++) {
            if (!allPlanets[i].getName().equals(environment.getCurrentPlanet().getName())) {
                fillPlanetPanelAndButtonGroup(allPlanets[i], buttonGroup, planetsPanel);
            }
        }
        return planetsPanel;
    }

    private JPanel getSelectCrewPanelGrid(ButtonGroup memberButtonGroup, CrewMember member) {
        JPanel memberPanel = new JPanel();
        memberPanel.setBorder(new LineBorder(new Color(119, 119, 119)));
        memberPanel.setLayout(null);

        JRadioButton memberRadio = new JRadioButton(member.getName());
        //memberRadio.setBounds(176, 147, 144, 23);
        memberRadio.setBounds(20, 170, 150, 23);
        if (!member.hasActionsLeft()) {
            memberRadio.setEnabled(false);
        }
        memberRadio.putClientProperty("CrewMember", member);

        JLabel memberImageLabel = new JLabel("");
        memberImageLabel.setBounds(0, 0, 150, 150);
        memberImageLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
        memberImageLabel.setIcon(new ImageIcon(Image.getCrewMemberImagePath(member)));

        memberButtonGroup.add(memberRadio);
        memberPanel.add(memberImageLabel);
        memberPanel.add(memberRadio);
        return memberPanel;
    }