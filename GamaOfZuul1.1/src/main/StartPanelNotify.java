package main;

import zuul.panels.PanelListener;
import Enums.ZuulEnums;
import Enums.ZuulEnums.Navigation;

public interface StartPanelNotify {
	public void setListener(PanelListener listener);
	public void notifyListener(Navigation toPanel);
}
