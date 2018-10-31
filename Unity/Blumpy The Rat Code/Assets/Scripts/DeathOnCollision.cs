using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;



public class DeathOnCollision : MonoBehaviour {

    
    public GameObject Door;
    public GameObject song;

    Animator anim;

    public bool dead = false;

    public playerController1 control;
    public Blink characterBlink;
    public Arrows arrows;

    void Start () {
        //Finding objects with specific tags for easier differentiation
        Door = GameObject.FindGameObjectWithTag("Door");
        song = GameObject.FindGameObjectWithTag("Audio");
        //Getting components of other scripts
        anim = GetComponent<Animator>();
        control = GetComponent<playerController1>();
        characterBlink = GetComponent<Blink>();
        arrows = GetComponent<Arrows>();

    }

	void Update () {

        //If the player is dead, disables other scripts to prevent blinking and moving after death
        if (dead==true)
        {
            control.enabled = false; //Disables control
            characterBlink.enabled = false; //Disables blinking
            arrows.enabled = false; //Disables arrow display
            gameObject.layer = 10; //Changes the layer of the player so that trap doesn't move towards dead player
        }
    }

    
    void OnCollisionEnter2D (Collision2D other)
    {
        if (other.gameObject.tag == "Goo")
        {
            dead = true;
            anim.SetBool("dead", true); //Plays death animation upon collision with traps and goo
            StartCoroutine(WaitBeforeNextScene(0.6f)); //For the death animation to play fully
        }

        if(other.gameObject.tag == "PickUp")
            {
            Destroy(other.gameObject); //Destroys the key upon contact
            Destroy(Door); //Destroy the exit door when key is picked up 
        }

        if (other.gameObject.tag == "Trigger")
        {
            Destroy(song); //Destroys awesome music
            SceneManager.LoadScene(SceneManager.GetActiveScene().buildIndex + 1); //Loads the next scene
        }
    }

    IEnumerator WaitBeforeNextScene(float time)
    {
        yield return new WaitForSeconds(time); //Waits for time given
        SceneManager.LoadScene(SceneManager.GetActiveScene().buildIndex); //Loads current scene
    }
}
