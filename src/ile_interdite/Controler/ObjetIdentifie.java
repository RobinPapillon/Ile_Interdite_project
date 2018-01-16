package ile_interdite.Controler;

import java.util.HashMap;

public abstract class ObjetIdentifie {
    private static Integer lastId = 1 ;
    protected Integer id ;
    private static HashMap<Integer, Object> listObjet = new HashMap();
    
    public ObjetIdentifie() {
       this.id = ObjetIdentifie.getNextId();
       listObjet.put(id, this);
    } 

    public Integer getId() {
        return this.id ;
    }

    private static Integer getNextId() {
        return lastId++ ;
    }

    private void setId(Integer id) {
        this.id = id ;
    }
    
    public static Object getFromId(Integer id)
    {
	return listObjet.get(id);
    }
    
}