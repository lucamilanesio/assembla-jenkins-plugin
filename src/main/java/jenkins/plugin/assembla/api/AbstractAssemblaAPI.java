package jenkins.plugin.assembla.api;

public abstract class AbstractAssemblaAPI {

	protected AssemblaHttpClient client;
	
	protected AssemblaSite assemblaSite;
	
	protected String space;
	
	public AbstractAssemblaAPI(AssemblaSite assemblaSite) {
		
		this.assemblaSite = assemblaSite;
		
		client = new AssemblaHttpClient(assemblaSite);
	}
}
