using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Arrows : MonoBehaviour {

    
    private GameObject arrclone;    //Variable for the arrow clone 
    GameObject player;      //Variable for player character (for making the clone a child of the player character)
    private bool exists;    //bool to make the clone instantiate only once

    //VARIABLES FOR CREATING THE ARROWS
    public GameObject ArrowRight;
    public GameObject ArrowLeft;
    public GameObject ArrowUp;
    public GameObject ArrowDown;

    //VARIABLES FOR CASTING THE RAYS
    Ray2D arrowRightCast;
    Ray2D arrowLeftCast;
    Ray2D arrowUpCast;
    Ray2D arrowDownCast;
    

    void Start () {
        player = GameObject.Find("Blumpy"); //Assigns the player character object (named "Blumpy") to "player"
        
    }
	
	
	void Update () {

        //CASTS 4 RAYS IN DIFFERENT DIRECTIONS FOR DETERMINING THE ARROW SPAWN POSITION
        arrowRightCast = new Ray2D(transform.position, Vector2.right);
        arrowLeftCast = new Ray2D(transform.position, Vector2.left);
        arrowUpCast = new Ray2D(transform.position, Vector2.up);
        arrowDownCast = new Ray2D(transform.position, Vector2.down);


        //CALLS FUNCTION WITH PARAMETERS DEPENDING ON THE DIRECTION PRESSED
        if (Input.GetKey(KeyCode.RightArrow))
        {

            ArrowSpawn(arrowRightCast, ArrowRight);
            

        }

        else if (Input.GetKey(KeyCode.LeftArrow))
        {
            ArrowSpawn(arrowLeftCast, ArrowLeft);

        }

        else if (Input.GetKey(KeyCode.UpArrow))
        {
            ArrowSpawn(arrowUpCast, ArrowUp);

        }

        else if (Input.GetKey(KeyCode.DownArrow))
        {
            ArrowSpawn(arrowDownCast, ArrowDown);

        }

        else
        {
            exists = false;     //Sets "exists" bool to false, so that the condition for spawning clones can be met
            Destroy(arrclone);  //Destroys the cloned object when button is not pressed
        }
    }


    //FUNCTION FOR SPAWNING ARROW
    void ArrowSpawn(Ray2D arrowplace, GameObject arrow)  //Takes in Ray2D (to determine position of arrow) and a gameobject to spawn a clone of
    {


        if (exists == false) //Condtion if "exists" bool is false
        {
            
            arrclone = Instantiate(arrow, arrowplace.GetPoint(0.8F), Quaternion.identity) as GameObject; //Creates an arrow next to the player according to the parameters
            arrclone.transform.parent = player.transform;  //Sets the created clone as child of the player, so that it follows around

              
            exists = true;  //Sets "bool" to true, so that it does not create any more of that object
        }
    }
}
