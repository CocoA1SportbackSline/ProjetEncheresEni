package fr.eni.ProjetEncheres.BLL;

public class BLLException extends Exception {
<<<<<<< HEAD
=======

>>>>>>> branch 'master' of https://github.com/CocoA1SportbackSline/ProjetEncheresEni.git

	//Constructeurs
		public BLLException() {
		super();
		}
		
		public BLLException(String message) {
		super(message);
		}
		
		public BLLException(String message, Throwable exception) {
		super(message, exception);
		}
		
		
		//M�thodes
		@Override
		public String getMessage() {
		StringBuffer sb = new StringBuffer("Couche DAL - ");
		sb.append(super.getMessage());
		
		return sb.toString() ;
		}
}

<<<<<<< HEAD
	

=======
>>>>>>> branch 'master' of https://github.com/CocoA1SportbackSline/ProjetEncheresEni.git
