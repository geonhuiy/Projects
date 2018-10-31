using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class playerController1 : MonoBehaviour
{
    public Rigidbody2D player;
    public int moveSpeed;

    //FOR ANIMATION
    private Animator animate;
    private bool moving;
    private Vector2 lastmove;
    //FOR ANIMATION

    void Start()
    {
        player = GetComponent<Rigidbody2D>(); // Character rigidbody
        animate = GetComponent<Animator>();  //Necessary for animator
        

    }

    // Update is called once per frame
    void Update()
    {
        //FOR MOVING THE CHARACTER
        
        float moveHorizontal = Input.GetAxis("Horizontal");
        float moveVertical = Input.GetAxis("Vertical");

       
        Vector2 movement = new Vector2(moveHorizontal, moveVertical);
        player.velocity = movement * moveSpeed;
        //FOR MOVING THE CHARACTER




        //FOR ANIMATION
        moving = false;
        if (Input.GetAxisRaw("Horizontal") > 0.5f || Input.GetAxisRaw("Horizontal") < -0.5f)    //If horizontal input happens
        {
            moving = true;  //Moving bool becomes true
            lastmove = new Vector2(Input.GetAxisRaw("Horizontal"), 0f);
        }

        if (Input.GetAxisRaw("Vertical") > 0.5f || Input.GetAxisRaw("Vertical") < -0.5f)    //If vertical input happens
        {
            moving = true;  //Moving bool becomes true
            lastmove = new Vector2(0f, Input.GetAxisRaw("Vertical"));   //last move becomes the last vertical input
        }
        animate.SetFloat("MoveX", Input.GetAxisRaw("Horizontal"));  //Send information to the animator
        animate.SetFloat("MoveY", Input.GetAxisRaw("Vertical"));
        animate.SetBool("moving", moving);
        animate.SetFloat("LastMoveX", lastmove.x);
        animate.SetFloat("LastMoveY", lastmove.y);
        //FOR ANIMATION
    }

    
}
   