package fr.prunetwork.network.server;

/**
 *
 * @author jpierre03
 */
public class MonServeurMultiClientTest{

	/**
	 * Constructeur par défaut
	 */
	public MonServeurMultiClientTest(){
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args){
//	Pour tester un protocole simple de communication
//	Bien entendu il faut que serveur et client soient compatibles

		/** Affiche ou non les messages échangés sur la console*/
		boolean afficheConsole = false;
		/**initialisation du serveur*/
		MonServeurMultiClient ms;
		if(args.length > 0){
			/** Récupère le n° de port depsui la console*/
			ms = new MonServeurMultiClient(Integer.parseInt(args[0]));
		} else{
			ms = new MonServeurMultiClient(0);
		}
		System.out.println("On communique");

		/** Envoie un message d'accueil*/
		ms.ecrireTousClient("Bienvenue sur MonServeur");

		/** Ecoute du client*/
		boolean continuer = true;
		/** Contient la ligne a échanger*/
		String ligne;
		while(continuer && ms != null && ms.getOnContinue()){
//			ligne = ms.lireClient();
//			if(ligne.equalsIgnoreCase("fin")){//peu importe la casse
//				continuer = false;
//				ms.ecrireTousClient("Au revoir");
//			}
//			if(afficheConsole){
//				System.out.println("Client : " + ligne);
//			}
//			ms.ecrireTousClient("recu :" + ligne);
			
		}
		System.out.println("On termine");
		ms.end();
	}
}
