/**
 * Created by linxi on 26/03/2018.
 */
(function () {
    /**
     * 更改左下角歌曲详情信息
     * @param songname
     * @param singer
     * @param albumsrc
     */
    function changeInfo(songname, singer, albumsrc) {
        document.getElementById("album_picture").style.backgroundImage = "url('" + albumsrc + "')"
        document.getElementById("singer").innerHTML = singer
        document.getElementById("song-name", songname).innerHTML = songname
    }

    /**
     * 一个封装的播放器的简易实现
     * @constructor
     */
    var Player = function () {
        this.activeSongNode = undefined
        this.audio = new Audio()
        this.play = function (src) {
            if (this.audio) {
                this.audio.pause()
            }
            this.audio = new Audio(src)
            this.audio.play()
        }

        this.pauseCurrrent = function () {
            if (this.audio) {
                this.audio.pause()
            }
        }

        this.playCurrent = function () {
            if (this.audio) {
                this.audio.play()
            }
        }
    }
    var player = new Player()

    /**
     * 查找指定名称的相关歌曲
     * @param keyword
     */
    function search(keyword) {
        $.ajax({
            url: "../../search",
            data: {
                keyword: keyword,
                type: 1,
                limit: 100,
                offset: 0
            },
            success: function (data) {
                console.log(data)
                if (data.code == 200) {
                    /**
                     * 正常返回值,ok
                     */
                    const result = data.result
                    let songs_list = document.getElementById("tbody-songs-list")
                    songs_list.innerHTML = "";
                    with (result) {
                        console.log(songCount)
                        let index = 1
                        var l = "<td>"
                        var r = "</td>"
                        /**
                         *  <tr>
                         <td>1</td>
                         <td>😀😆</td>
                         <td>等你下课 (with 杨瑞代)</td>
                         <td>周杰伦</td>
                         <td>《等你下课》</td>
                         <td>04:30</td>
                         <td></td>
                         </tr>
                         */
                        let temp = document.createDocumentFragment()
                        for (song of songs) {
                            console.log(song)
                            with (song) {
                                console.log(name)
                                console.log(ar[0].name)
                                console.log(al.picUrl)
                                console.log(dt / 1000 / 60)
                                var time = dt / 1000 / 60
                                var left = pad(parseInt(time), 2, 1)
                                var right = pad(((time % 1) * 60).toFixed(0), 2, 2)
                                time = left + ":" + right
                                var song_name_td = document.createElement("TD")
                                song_name_td.setAttribute("data-src", "http://music.163.com/song/media/outer/url?id=" + id + ".mp3")
                                song_name_td.setAttribute("data-albumpic", al.picUrl)
                                song_name_td.setAttribute("data-singer", ar[0].name)
                                song_name_td.setAttribute("data-albumname", al.name)
                                song_name_td.setAttribute("data-songname", name)
                                song_name_td.classList.add("song-name")
                                song_name_td.innerHTML = name
                                song_name_td.addEventListener("click", function () {
                                    /**
                                     * 记住当前正在播放的音乐所在节点
                                     * @type {Element}
                                     */
                                    if (player.activeSongNode) {
                                        player.activeSongNode.classList.remove("onplay")
                                    }
                                    console.log(this.dataset['src'])
                                    player.play(this.dataset['src'])
                                    changeInfo(this.dataset['songname'], this.dataset['singer'], this.dataset['albumpic'])
                                    var father = this.parentNode
                                    if (!father.classList.contains("onplay")) {
                                        father.classList.add("onplay")
                                    }
                                    player.activeSongNode = father
                                })

                                let tr = document.createElement("TR")
                                var t1 = document.createElement("td")
                                t1.innerHTML = index++
                                tr.appendChild(t1)
                                var t2 = document.createElement("td")
                                t2.innerHTML = "# @"
                                tr.append(t2)
                                tr.appendChild(song_name_td)
                                var t4 = document.createElement("td")
                                t4.innerHTML = ar[0].name
                                tr.appendChild(t4)
                                var t5 = document.createElement("td")
                                t5.innerHTML = al.name
                                tr.appendChild(t5)
                                var t6 = document.createElement("td")
                                t6.innerHTML = time
                                tr.appendChild(t6)
                                tr.appendChild(document.createElement("td"))
                                temp.appendChild(tr)
                            }
                        }
                        songs_list.innerHTML = ""
                        songs_list.appendChild(temp)
                    }
                }
            }
        })
    }

    window.onload = () => {
        document.getElementById("searchbox").onkeydown = function (event) {
            var e = event || window.event || arguments.callee.caller.arguments[0];
            console.log(e.key)
            if (e.key == "Enter") {
                search(document.getElementById("searchbox").value.trim())
            }
        }
    }
    function pad(num, n, type) {
        if (type == 1) {
            var a = '000000000000000' + num;
            a = a.substring(a.length - n);
            return a;
        } else {
            var a = num + "0000000000000";
            a = a.substr(0, n)
            return a;
        }
    }

    search("周杰伦")
})()