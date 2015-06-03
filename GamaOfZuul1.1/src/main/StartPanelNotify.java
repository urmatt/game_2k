package main;

import zuul.panels.PanelListener;
import Enums.PanelNavigation;
import Enums.PanelNavigation.Navigation;

public interface StartPanelNotify {
	public void setListener(PanelListener listener);
	public void notifyListener(Navigation toPanel);
}
