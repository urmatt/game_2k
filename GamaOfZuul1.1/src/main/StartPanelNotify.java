package main;

import Enums.ZuulEnums.Navigation;
import zuul.panels.PanelListener;

public interface StartPanelNotify {
	public void setListener(PanelListener listener);
	public void notifyListener(Navigation toPanel);
}
