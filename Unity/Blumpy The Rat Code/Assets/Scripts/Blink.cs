using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Blink : MonoBehaviour {

    
    public float shiftrange;    //Defines the distance for the teleport
    private float temprange;    //Variable for storing the temporary value of the teleport distance
    private bool exists;            //Bool for instantiating the projection image
    public GameObject proj;         //Define object to be cloned for the projection
    private GameObject projclone;    //Defines clone of projection

    Vector2 position;           //Variable for the player position

    Vector2 blinkright;     //Vector variables for blinking direction
    Vector2 blinkleft;
    Vector2 blinkup;
    Vector2 blinkdown;

    Ray2D projection;       //Raycast used for projecting the teleport distance
    RaycastHit2D hit;   

    public float growthRate;  //Float for rate at which the raycast grows back to full length
    

    void Start () {

        temprange = shiftrange;        // Sets temprange to default teleport distance (shiftrange)

    }


    void Update()
    {
        if(exists) //When bool "exists" is set to true, i.e. a key is held down, calls the UpdateProjectionLocation function to change the projected image's position
        {
            UpdateProjectionLocation();
        }

        //ASSIGNS VECTORS TO THE BLINKING DIRECTIONS
        blinkright = new Vector2(temprange, 0);
        blinkleft = new Vector2(-temprange, 0);
        blinkup = new Vector2(0f, temprange);
        blinkdown = new Vector2(0f, -temprange);

        position = transform.position;  //sets "position" as transform.position (character's position)


        //CALLS THE BLINK FUNCTION AND INPUTS PARAMETERS ACCORDING TO THE DIRECTION
        if (Input.GetKey(KeyCode.RightArrow))
        {
            BlinkDir(Vector2.right, blinkright, blinkright);
        }

        else if (Input.GetKey(KeyCode.LeftArrow))
        {
            BlinkDir(Vector2.left, blinkleft, blinkleft);
        }

        else if (Input.GetKey(KeyCode.UpArrow))
        {
            BlinkDir(Vector2.up, blinkup, blinkup);
        }

        else if (Input.GetKey(KeyCode.DownArrow))
        {
            BlinkDir(Vector2.down, blinkdown, blinkdown);
        }


        //IF KEYS ARE NOT HELD DOWN, SETS THE EXIST BOOL TO FALSE (SO THE PROJECTION DOESN'T INSTANTIATE), RESUMES NORMAL TIME FLOW AND DESTROYS THE PROJECTION IMAGE
        else
        {            
            exists = false;
            Destroy(projclone);
            Time.timeScale = 1f;            
        }


    }

    void BlinkDir (Vector2 direction, Vector2 blinkdir, Vector2 debugl)
    {


        projection = new Ray2D(position, direction);    //Casts a ray from the character position into a direction according to the inputted parameters
        hit = Physics2D.Raycast(position, direction, temprange, 1 << LayerMask.NameToLayer("Walls")); //Raycast2D for determining the length of the raycast and checking for walls

        Debug.DrawRay(position, debugl, Color.black);
        Time.timeScale = 0.3f;  //Slows down time when arrow key is held down

       
        if (hit.collider != null) //When the raycast hits a collider
        {
            Debug.Log("hitright");          //Console shows that the ray hit a collider
            Debug.Log(hit.point);    //Console shows where the ray the collider
            temprange = hit.distance;    //Sets temprange as the distance between the ray source(character) and the collision point
        }

        else
        {
            temprange += Time.deltaTime * growthRate; //Makes ray grow according to growth rate until in hit shiftrange length or wall collider
            print("leghtening "+temprange);
            if(temprange >= shiftrange)
            {
                print("stopped lengthening");   //Stops ray growth when it reaches max length (shiftrange)
                temprange = shiftrange;
            }
        }

        if (exists == false) //Condtion if "exists" bool is false
        {            
            projclone = Instantiate(proj, projection.GetPoint(temprange), Quaternion.identity) as GameObject;   //Creates an object "projection" at temprange                     
            exists = true;  //Sets "bool" to true, so that it does not create any more of that object
            projclone.transform.position = transform.position;
        }

        if (Input.GetKeyDown(KeyCode.LeftShift))    //Press shift to teleport
        {
            transform.Translate(blinkdir);    //Teleports the character by the teleport distance (temprange)
        }


    }

    //MOVING THE PROJECTED OBJECT ACCORDING TO THE RAYCAST
    void UpdateProjectionLocation()
    {
        if(hit) //Puts projected image at point where the ray collides with the wall
        {
            projclone.transform.position = hit.point;

        } else
        projclone.transform.position = projection.GetPoint(shiftrange);     //Sets the projected image where the raycast ends (max distance)
    }
   
}
