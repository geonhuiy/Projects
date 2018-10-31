using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TrapDown : MonoBehaviour
{

    public Transform sightStart, sightEnd, originalPos;
    
    public bool spotted = false; //detects if Blumpy is in the raycast of the trap
    public bool spotted1 = false;
    public int speed = 2;

    void Update()
    {
        RayCasting();
        Behaviours();
    }

    void RayCasting()
    {
        Debug.DrawLine(sightStart.position, sightEnd.position, Color.red);
        Debug.DrawLine(sightStart.position, originalPos.position, Color.blue);
        spotted = Physics2D.Linecast(sightStart.position, sightEnd.position, 1 << 2);       // if linecast detects a collider, bool spotted will be set to true
        spotted1 = Physics2D.Linecast(sightStart.position, originalPos.position, 1 << 2);   //if line cast to original position spots detects a collider, trap moves there
    }

    void Behaviours()
    {
        if (spotted == true)
        {
            transform.Translate(Vector2.down * speed * Time.deltaTime);
        }
        if (spotted1 == true)
        {
            transform.Translate(Vector2.up * speed * Time.deltaTime);
        }
    }

}
