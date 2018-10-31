using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SceneLoader : MonoBehaviour {

    // To load a given level
    public void LoadScene(int level)
    {
        Application.LoadLevel(level);
    }

    // To exit the game
    public void quitGame()
    {
        Application.Quit();
    }
}
