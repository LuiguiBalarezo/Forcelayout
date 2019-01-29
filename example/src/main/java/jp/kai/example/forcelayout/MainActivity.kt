package jp.kai.example.forcelayout

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import jp.kai.forcelayout.Forcelayout
import jp.kai.forcelayout.Links
import jp.kai.forcelayout.Links.LinkPair
import jp.kai.forcelayout.Nodes
import jp.kai.forcelayout.Nodes.NodePair

/**
 * Created by kai on 2016/09/03.
 * Usage for Kotlin
 */

class MainActivity : Activity() {
    private var isFlip: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val force: Forcelayout = findViewById(R.id.forcelayout) as Forcelayout
        example1(force)

        val button: Button? = findViewById(R.id.button) as? Button
        val seekBar: SeekBar? = findViewById(R.id.seekbar) as? SeekBar

        button?.setOnClickListener {
            isFlip = if (isFlip) {
                example1(force)
                false
            } else {
                example2(force)
                true
            }
        }

        seekBar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                force.with().gravity((progress.toFloat() / 1000).toDouble()).start()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }

    private fun example1(force: Forcelayout) {
        /** set nodes */
        val nodes: Nodes = Nodes()
        nodes.add(NodePair("cat_a1", R.drawable.a))
        nodes.add(NodePair("cat_a2", R.drawable.a))
        nodes.add(NodePair("cat_a3", R.drawable.a))
        nodes.add(NodePair("cat_b1", R.drawable.b))
        nodes.add(NodePair("cat_c1", R.drawable.c))
        nodes.add(NodePair("cat_d1", R.drawable.d))
        nodes.add(NodePair("cat_d2", R.drawable.d))
        nodes.add(NodePair("cat_e1", R.drawable.e))
        nodes.add(NodePair("cat_f1", R.drawable.f))

        /** set links */
        val links: Links = Links()
        links.add(LinkPair("cat_a1", "cat_a2"))
        links.add(LinkPair("cat_a2", "cat_a3"))
        links.add(LinkPair("cat_a1", "cat_a3"))
        links.add(LinkPair("cat_d1", "cat_d2"))

        force.with()
                .distance(350)
                /** distance between each nodes */
                .gravity(0.04)
                /** gravitation from center of view */
                .friction(0.02)
                /** value of gravitation between each nodes */
                .size(130)
                /** node width */
                .nodes(nodes)
                /** set nodes */
                .links(links)
                /** set links */
                .start()
        /** start animation */
    }


    private fun example2(force: Forcelayout) {
        /** set nodes */
        val nodes: Array<String> = arrayOf("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r")

        /** set links */
        val links: Links = Links()
        links.add(Links.LinkPair("a", "b"))
        links.add(Links.LinkPair("b", "c"))
        links.add(Links.LinkPair("a", "c"))
        links.add(Links.LinkPair("d", "e"))
        links.add(Links.LinkPair("d", "g"))
        links.add(Links.LinkPair("d", "l"))
        links.add(Links.LinkPair("l", "k"))
        links.add(Links.LinkPair("l", "m"))
        links.add(Links.LinkPair("l", "n"))
        links.add(Links.LinkPair("h", "i"))
        links.add(Links.LinkPair("o", "p"))
        links.add(Links.LinkPair("p", "q"))
        links.add(Links.LinkPair("q", "r"))
        links.add(Links.LinkPair("r", "o"))

        force.node()
                .size(100)
                .style(Color.argb(100, 200, 30, 50))

        force.link()
                .style(Color.argb(60, 50, 30, 200), 5.0f)

        force.with()
                .distance(350)
                /** distance between each nodes */
                .gravity(0.04)
                /** gravitation from center of view */
                .friction(0.02)
                /** value of gravitation between each nodes */
                .nodes(nodes)
                /** set nodes */
                .links(links)
                /** set links */
                .start()
        /** start animation */
    }
}