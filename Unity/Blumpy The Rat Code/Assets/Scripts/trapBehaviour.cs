using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class trapBehaviour : MonoBehaviour {

    public Transform onTrap, sightEnd, sightStart;

    public bool spotted = false; //detects if Blumpy is in the raycast of the trap
    public bool spotted1 = false; //also detects if Blumpy is in the raycast
    public int speed = 2; //Speed of the trap

	void Update ()
    {
        RayCasting();
        Behaviours();
	}

    void RayCasting ()
    {
        //Debug Lines
        Debug.DrawLine(onTrap.position, sightEnd.position, Color.red);
        Debug.DrawLine(onTrap.position, sightStart.position, Color.blue);
        //Debug Lines

        spotted = Physics2D.Linecast(onTrap.position, sightEnd.position, 1 << 2);// if linecast detects a collider, bool spotted will be set to true
        spotted1 = Physics2D.Linecast(onTrap.position, sightStart.position, 1 << 2);
    }

    void Behaviours ()
    {
        //When spotted, trap will move to the left
        if (spotted == true)
        {
            transform.Translate(Vector2.left * speed * Time.deltaTime);
        }

        //When spotted, trap will move to the right
        if (spotted1 == true)
        {
            transform.Translate(Vector2.right * speed * Time.deltaTime);
        }
    }
    
}
