package jenkins.plugin.assembla;

import hudson.Plugin;

public class AssemblaPlugin extends Plugin {

	private transient AssemblaSCMListener scmListener;
	
	@Override
	public void start() throws Exception {

		scmListener = new AssemblaSCMListener();
		scmListener.register();

		super.start();
	}
	
	@Override
	public void stop() throws Exception {
	
		scmListener.unregister();
		
		super.stop();
	}
}
