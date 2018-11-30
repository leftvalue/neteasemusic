/**
 * Created by linxi on 30/03/2018.
 */
window.onload = function () {
    var genCssSelector = function (t) {
        var array = new Array();
        array.push(t)
        while (true) {
            var p = t.parentNode;
            if (p.tagName != undefined) {
                array.push(p);
                t = p;
            } else {
                break
            }
        }
        var str = "";
        while(array.length>0) {
            v=array.pop()
            if (v.id) {
                str += "#" + v.id
            } else if (v.className) {
                str += "." + v.className.split(" ").join(".")
            } else {
                str += v.tagName.toLocaleLowerCase()
            }
            str += " "
        }
        return str.substr(0,str.length-1)
    }

    document.addEventListener('click', function (e) {
        //点击li时，返回：html body #page .content.main .refer ul li
        console.log(genCssSelector(e.target));
    })
}