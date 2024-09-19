var container = document.getElementsByClassName("bars-container")[0];

function drawBar(initialPos, barHeight) {
    let bar = document.createElementNS('http://www.w3.org/2000/svg', 'rect');
    bar.setAttribute("x", initialPos*barWidth);
    bar.setAttribute("y", 0);
    bar.setAttribute("width", barWidth);
    bar.setAttribute("height", barHeight);
    bar.classList.add("rect");
    return bar;
}

function createBarChart(barsInfo, svg) {
    for(let barInfo of barsInfo) {
        bar = drawBar(barInfo.initialPos, barInfo.barHeight);
        bar.style.fill = "rgb(108, 108, 201)";
        bar.addEventListener("mouseover", (event) => {
            /* We should prevent the colour change of the bars on mouseover after the 
            sort has started. */
            if(!sortStarted)
                event.target.style.fill = "rgb(34, 149, 86)";
        });
        bar.addEventListener("mouseout", (event)=> {
            /* We should prevent the colour change of the bars on mouseout after the 
            sort has started. */
            if(!sortStarted)
                event.target.style.fill = "rgb(108, 108, 201)";
        });
        svg[0].appendChild(bar);
    }
}

function generateBarsInfo(noOfBars) {
    let barMinHeight = 0.2*container.clientHeight;
    let barMaxHeight = 0.95*container.clientHeight;
    console.log("barMinHeight: " + barMinHeight);
    console.log("barMaxHeight: " + barMaxHeight);
    for(let i=0; i<noOfBars; i++) {
        // bar height should lie in the range barMinHeight and barMaxHeight
        let num = Math.floor(Math.random()*(barMaxHeight-barMinHeight))+barMinHeight;
        let barInfo = {};
        barInfo.barHeight = num;
        barInfo.initialPos = i;
        barsInfo.push(barInfo);
    }
}

// function to swap two bars
async function swapBars(idx1, idx2) {
    /* Note: These bar1 and bar2 which hold the reference to the "rect" elements in the 
    DOM must be declared let or const otherwise there may be synchronization problems
    while using sorting algorithms where recursion is there like the QuickSort.
    These synchronization problems would distort the visual representation of the 
    sorted array so the bar chart would not appear sorted even though in the "barsInfo"
    array the elements are sorted.  */
    let bar1 = bars[idx1];
    let bar2 = bars[idx2];

    /* Changing the color of the two bars that we are going to swap. */
    bar1.style.fill = "rgb(34, 149, 86)";
    bar2.style.fill = "rgb(34, 149, 86)";


    /* Waiting before we swap so as to give a visual indication of the two bars that we 
    are going to swap. */
    await wait();

    // swapping the bar heights in DOM
    bar1.setAttribute("height", barsInfo[idx1].barHeight);
    bar2.setAttribute("height", barsInfo[idx2].barHeight);

    /* Waiting before changing the colour to normal of the two bars that have been 
    swapped currently. */
    await wait();
    bar1.style.fill = "rgb(108, 108, 201)";
    bar2.style.fill = "rgb(108, 108, 201)";
}

function wait() {
    return new Promise(resolve=> {
        setTimeout(()=> resolve(), sortSpeed);
    });
}

async function bubbleSort() {
    for(let i=barsInfo.length-1; i>0; i--) {
        for(j=0; j<i; j++) {
            if(barsInfo[j].barHeight > barsInfo[j+1].barHeight) {
                // swapping the bars in barsInfo array based on the bar height
                [barsInfo[j], barsInfo[j+1]] = [barsInfo[j+1], barsInfo[j]];
                // console.log("swapBars(" + j + ", " + (j+1) + ")");
                const result = await swapBars(j, j+1);
                // console.log(result);
            }
        }
    }
}

async function insertionSort() {
    for(let i=0; i<barsInfo.length-1; i++) {
        for(j=i+1; j>0; j--) {
            if(barsInfo[j].barHeight < barsInfo[j-1].barHeight) {
                // swapping the bars in barsInfo array based on the bar height
                [barsInfo[j-1], barsInfo[j]] = [barsInfo[j], barsInfo[j-1]];
                // console.log("swapBars(" + (j-1) + ", " + j + ")");
                const result = await swapBars(j-1, j);
                // console.log(result);
            }
            else {
                break;
            }
        }
    }
}

async function selectionSort() {
    for(let i=0; i<barsInfo.length-1; i++) {
        let k = i;
        for(j=i+1; j<barsInfo.length; j++) {
            if(barsInfo[k].barHeight > barsInfo[j].barHeight) {
                k = j;
            }
        }
        if(i != k) {
            // swapping the bars in barsInfo array based on the bar height
            [barsInfo[i], barsInfo[k]] = [barsInfo[k], barsInfo[i]];
            // console.log("swapBars(" + i + ", " + (k) + ")");
            const result = await swapBars(i, k);
            // console.log(result);
        }
    }
}

async function partition(startIdx, endIdx) {
    let pivot=startIdx, i=startIdx, j=endIdx;
    while(j > i) {
        while(i <= endIdx && barsInfo[i].barHeight <= barsInfo[pivot].barHeight) {
            i++;
        }
        while(j >= startIdx && barsInfo[j].barHeight >= barsInfo[pivot].barHeight) {
            j--;
        }
        if((i <= endIdx && j >= startIdx) && i < j) {
            // swapping the bars in barsInfo array based on the bar height
            [barsInfo[i], barsInfo[j]] = [barsInfo[j], barsInfo[i]];
            const result = await swapBars(i, j);
        }
    }

    if(j > startIdx) {
        [barsInfo[pivot], barsInfo[j]] = [barsInfo[j], barsInfo[pivot]];
        const result = await swapBars(pivot, j);
    }

    if(j >= startIdx) return j;
    
    return startIdx;
}

async function quickSort(startIdx, endIdx) {
    console.log("Inside Quick Sort");
    if(startIdx >= 0 && endIdx >= 0) {
        if(endIdx > startIdx) {
            let pos = await partition(startIdx, endIdx);
            console.log("pos: " + pos);
            /* Note it is not necessary to wait for the first recursion i.e. 
            quickSort(startIdx, pos-1); to complete before starting the second recursion
            quickSort(pos+1, endIndex); because the subarray on which the 
            partion() algorithm will be applied are mutually exclusive hence in the visuals
            we may see more than one pair of bars being dealt with at a time. Thus this
            approach gives the feeling of tasks executing concurrently on different 
            threads.*/
            quickSort(startIdx, pos-1);
            quickSort(pos+1, endIdx);
        }
    }
}

async function changeBarHeight(idx, height) {
    let bar = bars[idx];
    bar.style.fill = "rgb(34, 149, 86)";

    await wait();

    bar.setAttribute("height", height);

    await wait();
    bar.style.fill = "rgb(108, 108, 201)";

}

async function merge(start, mid, end) {
    let arr1 = Array(mid-start+1), arr2 = Array(end-mid);

    // initializing the starting indices for the subarrays
    let i=0, j=0, k=start;
    while(i<(mid-start+1)) {
        arr1[i] = barsInfo[k];
        i++;
        k++;
    }

    while(j<(end-mid)) {
        arr2[j] = barsInfo[k];
        j++;
        k++;
    }

    // initializing the starting index before starting the merging of sorted subarrays
    i=0, j=0, k=start;
    while(i<(mid-start+1) && j<(end-mid)) {
        if(arr1[i].barHeight > arr2[j].barHeight) {
            barsInfo[k] = arr2[j];
            const result = await changeBarHeight(k, arr2[j].barHeight);
            j++;
        }
        else {
            barsInfo[k] = arr1[i];
            const result = await changeBarHeight(k, arr1[i].barHeight);
            i++;
        }
        k++;
    }

    while(i < (mid-start+1)) {
        barsInfo[k] = arr1[i];
        const result = await changeBarHeight(k, arr1[i].barHeight);
        i++;
        k++;
    }

    while(j < (end-mid)) {
        barsInfo[k] = arr2[j];
        const result = await changeBarHeight(k, arr2[j].barHeight);
        j++;
        k++;
    }
}

async function mergeSort(start, end) {
    if((start >=0 && end >= 0) && (start <= end)) {
        if(start == end)
            return;
        let mid = Math.floor((start + end)/2);
        /* Note: We can't use just mergeSort(start, mid); and mergeSort(mid+1, end); 
        because merging can't be done until we get two sorted subarrays. */
        await mergeSort(start, mid);
        await mergeSort(mid+1, end);
        await merge(start, mid, end);
    }
}

async function startSort() {
    if(!sortStarted && barsSet) {
        let sortType = document.getElementById("sortType").value;
        console.log("sortType: |" + sortType  + "|");

        sortStarted = true;
        console.log("barsInfo.length: " + barsInfo.length);
        setSortSpeed();

        let result;
        switch (sortType) {
            case "Bubble Sort":
                result = await bubbleSort();
                console.log(result);
                break;
            case "Insertion Sort":
                result = await insertionSort();
                console.log(result);
                break;
            case "Selection Sort":
                result = await selectionSort();
                console.log(result);
                break;
            case "Quick Sort":
                result = await quickSort(0, noOfBars-1);
                console.log(result);
                break;
            case "Merge Sort":
                result = await mergeSort(0, noOfBars-1);
                console.log(result);
                break;
            default:
                console.log("Invalid sortType: |" + sortType  + "|");
        }

        console.log(sortType + " completed!");
        console.log(barsInfo);
        sortStarted = false;
    }
    else {
        console.log("Bars have not been set!");
    }
}

function removeAllBars() {
    if(!sortStarted && barsSet) {
        while (svg[0].hasChildNodes()) {
            svg[0].removeChild(svg[0].firstChild);
        }
        barsSet = false;
        /* Empty the barsInfo array when removeAllBars() is called otherwise we would have the
        previously generated bars info too.  */
        barsInfo.splice(0, barsInfo.length);
    }
}

function setBars() {
    if(!barsSet && !sortStarted) {
        barsSet = true;
        barWidth = container.clientWidth/noOfBars;
        console.log("container.clientWidth:" + container.clientWidth);
        console.log("container.clientHeight:" + container.clientHeight);
        console.log("noOfBars: " + noOfBars);
        console.log("barWidth:" + barWidth);

        generateBarsInfo(noOfBars);
        createBarChart(barsInfo, svg);
        console.log(barsInfo);
        setSortSpeed();
    }
}

function setSortSpeed() {
    sortSpeed = +document.getElementById("sortSpeed").value;
    console.log("sortSpeed: " + sortSpeed + " milliseconds");
}

function resetBars() {
    noOfBars = +document.getElementById("numberOfBarsDropdown").value;
    removeAllBars();
    setBars();
}

var noOfBars = 10;
var barWidth;
var sortSpeed = 0;
var sortStarted = false;
var barsSet = false;
var barsInfo = [];

svg = document.getElementsByTagName("svg");

/* getElementsByTagName() return a live collection, meaning that it reflects 
the current state of the DOM. The collection updates automatically when elements 
are added or removed from the document, even though the getElementsByTagName("rect") 
function is not run again.
*/
bars = document.getElementsByTagName("rect");

// var barsInfo = [
//     {
//         barHeight: 5,
//         initialPos: 0
//     },
//     {
//         barHeight: 8,
//         initialPos: 1
//     },
//     {
//         barHeight: 1,
//         initialPos: 2
//     },
//     {
//         barHeight: 6,
//         initialPos: 3
//     },
//     {
//         barHeight: 4,
//         initialPos: 4
//     },
//     {
//         barHeight: 2,
//         initialPos: 5
//     },
//     {
//         barHeight: 7,
//         initialPos: 6
//     }
// ];

// console.log(barsInfo);