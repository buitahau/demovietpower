$(document).ready(function(){
    var bubbleSort = new BubbleSort($("#bubble_canvas"));
    bubbleSort.render();

    drawCanvasSun();

    drawCanvasWaterfall();

    drawCanvasLighting();

});

function drawCanvasLighting(){
    /*=============================================================================*/
    /* Canvas Lightning v1
     /*=============================================================================*/
    var canvasLightning = function(c, cw, ch){

        /*=============================================================================*/
        /* Initialize
         /*=============================================================================*/
        this.init = function(){
            this.loop();
        };

        /*=============================================================================*/
        /* Variables
         /*=============================================================================*/
        var _this = this;
        this.c = c;
        this.ctx = c.getContext('2d');
        this.cw = cw;
        this.ch = ch;
        this.mx = 0;
        this.my = 0;

        this.lightning = [];
        this.lightTimeCurrent = 0;
        this.lightTimeTotal = 50;

        /*=============================================================================*/
        /* Utility Functions
         /*=============================================================================*/
        this.rand = function(rMi, rMa){return ~~((Math.random()*(rMa-rMi+1))+rMi);};
        this.hitTest = function(x1, y1, w1, h1, x2, y2, w2, h2){return !(x1 + w1 < x2 || x2 + w2 < x1 || y1 + h1 < y2 || y2 + h2 < y1);};

        /*=============================================================================*/
        /* Create Lightning
         /*=============================================================================*/
        this.createL= function(x, y, canSpawn){
            this.lightning.push({
                x: x,
                y: y,
                xRange: this.rand(5, 30),
                yRange: this.rand(5, 25),
                path: [{
                    x: x,
                    y: y
                }],
                pathLimit: this.rand(10, 35),
                canSpawn: canSpawn,
                hasFired: false
            });
        };

        /*=============================================================================*/
        /* Update Lightning
         /*=============================================================================*/
        this.updateL = function(){
            var i = this.lightning.length;
            while(i--){
                var light = this.lightning[i];


                light.path.push({
                    x: light.path[light.path.length-1].x + (this.rand(0, light.xRange)-(light.xRange/2)),
                    y: light.path[light.path.length-1].y + (this.rand(0, light.yRange))
                });

                if(light.path.length > light.pathLimit){
                    this.lightning.splice(i, 1)
                }
                light.hasFired = true;
            }
        };

        /*=============================================================================*/
        /* Render Lightning
         /*=============================================================================*/
        this.renderL = function(){
            var i = this.lightning.length;
            while(i--){
                var light = this.lightning[i];

                this.ctx.strokeStyle = 'hsla(0, 100%, 100%, '+this.rand(10, 100)/100+')';
                this.ctx.lineWidth = 1;
                if(this.rand(0, 30) == 0){
                    this.ctx.lineWidth = 2;
                }
                if(this.rand(0, 60) == 0){
                    this.ctx.lineWidth = 3;
                }
                if(this.rand(0, 90) == 0){
                    this.ctx.lineWidth = 4;
                }
                if(this.rand(0, 120) == 0){
                    this.ctx.lineWidth = 5;
                }
                if(this.rand(0, 150) == 0){
                    this.ctx.lineWidth = 6;
                }

                this.ctx.beginPath();

                var pathCount = light.path.length;
                this.ctx.moveTo(light.x, light.y);
                for(var pc = 0; pc < pathCount; pc++){

                    this.ctx.lineTo(light.path[pc].x, light.path[pc].y);

                    if(light.canSpawn){
                        if(this.rand(0, 100) == 0){
                            light.canSpawn = false;
                            this.createL(light.path[pc].x, light.path[pc].y, false);
                        }
                    }
                }

                if(!light.hasFired){
                    this.ctx.fillStyle = 'rgba(255, 255, 255, '+this.rand(4, 12)/100+')';
                    this.ctx.fillRect(0, 0, this.cw, this.ch);
                }

                if(this.rand(0, 30) == 0){
                    this.ctx.fillStyle = 'rgba(255, 255, 255, '+this.rand(1, 3)/100+')';
                    this.ctx.fillRect(0, 0, this.cw, this.ch);
                }

                this.ctx.stroke();
            }
        };

        /*=============================================================================*/
        /* Lightning Timer
         /*=============================================================================*/
        this.lightningTimer = function(){
            this.lightTimeCurrent++;
            if(this.lightTimeCurrent >= this.lightTimeTotal){
                var newX = this.rand(100, cw - 100);
                var newY = this.rand(0, ch / 2);
                var createCount = this.rand(1, 3);
                while(createCount--){
                    this.createL(newX, newY, true);
                }
                this.lightTimeCurrent = 0;
                this.lightTimeTotal = this.rand(30, 100);
            }
        };

        /*=============================================================================*/
        /* Clear Canvas
         /*=============================================================================*/
        this.clearCanvas = function(){
            this.ctx.globalCompositeOperation = 'destination-out';
            this.ctx.fillStyle = 'rgba(0,0,0,'+this.rand(1, 30)/100+')';
            this.ctx.fillRect(0,0,this.cw,this.ch);
            this.ctx.globalCompositeOperation = 'source-over';
        };

        /*=============================================================================*/
        /* Resize on Canvas on Window Resize
         /*=============================================================================*/
        $(window).on('resize', function(){
            _this.cw = _this.c.width = window.innerWidth;
            _this.ch = _this.c.height = window.innerHeight;
        });

        /*=============================================================================*/
        /* Animation Loop
         /*=============================================================================*/
        this.loop = function(){
            var loopIt = function(){
                requestAnimationFrame(loopIt, _this.c);
                _this.clearCanvas();
                _this.updateL();
                _this.lightningTimer();
                _this.renderL();
            };
            loopIt();
        };

    };

    /*=============================================================================*/
    /* Check Canvas Support
     /*=============================================================================*/
    var isCanvasSupported = function(){
        var elem = document.createElement('canvas');
        return !!(elem.getContext && elem.getContext('2d'));
    };

    /*=============================================================================*/
    /* Setup requestAnimationFrame
     /*=============================================================================*/
    var setupRAF = function(){
        var lastTime = 0;
        var vendors = ['ms', 'moz', 'webkit', 'o'];
        for(var x = 0; x < vendors.length && !window.requestAnimationFrame; ++x){
            window.requestAnimationFrame = window[vendors[x]+'RequestAnimationFrame'];
            window.cancelAnimationFrame = window[vendors[x]+'CancelAnimationFrame'] || window[vendors[x]+'CancelRequestAnimationFrame'];
        };

        if(!window.requestAnimationFrame){
            window.requestAnimationFrame = function(callback, element){
                var currTime = new Date().getTime();
                var timeToCall = Math.max(0, 16 - (currTime - lastTime));
                var id = window.setTimeout(function() { callback(currTime + timeToCall); }, timeToCall);
                lastTime = currTime + timeToCall;
                return id;
            };
        };

        if (!window.cancelAnimationFrame){
            window.cancelAnimationFrame = function(id){
                clearTimeout(id);
            };
        };
    };

    /*=============================================================================*/
    /* Define Canvas and Initialize
     /*=============================================================================*/
    // $(window).load(function(){
        if(isCanvasSupported){
            var c = document.getElementById('canvas_lighter');
            var cw = c.width = window.innerWidth;
            var ch = c.height = window.innerHeight;
            var cl = new canvasLightning(c, cw, ch);

            setupRAF();
            cl.init();
        }
    // });
}

function drawCanvasWaterfall(){
    var waterfallCanvas = function(c, cw, ch){

        var _this = this;
        this.c = c;
        this.ctx = c.getContext('2d');
        this.cw = cw;
        this.ch = ch;

        this.particles = [];
        this.particleRate = 6;
        this.gravity = .15;


        this.init = function(){
            this.loop();
        };

        this.reset = function(){
            this.ctx.clearRect(0,0,this.cw,this.ch);
            this.particles = [];
        };

        this.rand = function(rMi, rMa){return ~~((Math.random()*(rMa-rMi+1))+rMi);};


        this.Particle = function(){
            var newWidth = _this.rand(1,20);
            var newHeight = _this.rand(1, 45);
            this.x = _this.rand(10+(newWidth/2), _this.cw-10-(newWidth/2));
            this.y = -newHeight;
            this.vx = 0;
            this.vy = 0;
            this.width = newWidth;
            this.height = newHeight;
            this.hue = _this.rand(200, 220);
            this.saturation = _this.rand(30, 60);
            this.lightness = _this.rand(30, 60);
        };

        this.Particle.prototype.update = function(i){
            this.vx += this.vx;
            this.vy += _this.gravity;
            this.x += this.vx;
            this.y += this.vy;
        };

        this.Particle.prototype.render = function(){
            _this.ctx.strokeStyle = 'hsla('+this.hue+', '+this.saturation+'%, '+this.lightness+'%, .05)';
            _this.ctx.beginPath();
            _this.ctx.moveTo(this.x, this.y);
            _this.ctx.lineTo(this.x, this.y + this.height);
            _this.ctx.lineWidth = this.width/2;
            _this.ctx.lineCap = 'round';
            _this.ctx.stroke();
        };

        this.Particle.prototype.renderBubble = function(){
            _this.ctx.fillStyle = 'hsla('+this.hue+', 40%, 40%, 1)';
            _this.ctx.fillStyle = 'hsla('+this.hue+', '+this.saturation+'%, '+this.lightness+'%, .3)';
            _this.ctx.beginPath();
            _this.ctx.arc(this.x+this.width/2, _this.ch-20-_this.rand(0,10), _this.rand(1,8), 0, Math.PI*2, false);
            _this.ctx.fill();
        };

        this.createParticles = function(){
            var i = this.particleRate;
            while(i--){
                this.particles.push(new this.Particle());
            }
        };

        this.removeParticles = function(){
            var i = this.particleRate;
            while(i--){
                var p = this.particles[i];
                if(p.y > _this.ch-20-p.height){
                    p.renderBubble();
                    _this.particles.splice(i, 1);
                }
            }
        };

        this.updateParticles = function(){
            var i = this.particles.length;
            while(i--){
                var p = this.particles[i];
                p.update(i);
            }
        };

        this.renderParticles = function(){
            var i = this.particles.length;
            while(i--){
                var p = this.particles[i];
                p.render();
            }
        };

        this.clearCanvas = function(){
            this.ctx.globalCompositeOperation = 'destination-out';
            this.ctx.fillStyle = 'rgba(255,255,255,.06)';
            this.ctx.fillRect(0,0,this.cw,this.ch);
            this.ctx.globalCompositeOperation = 'lighter';
        };

        this.loop = function(){
            var loopIt = function(){
                requestAnimationFrame(loopIt, _this.c);
                _this.clearCanvas();
                _this.createParticles();
                _this.updateParticles();
                _this.renderParticles();
                _this.removeParticles();
            };
            loopIt();
        };

    };

    var isCanvasSupported = function(){
        var elem = document.createElement('canvas');
        return !!(elem.getContext && elem.getContext('2d'));
    };

    var setupRAF = function(){
        var lastTime = 0;
        var vendors = ['ms', 'moz', 'webkit', 'o'];
        for(var x = 0; x < vendors.length && !window.requestAnimationFrame; ++x){
            window.requestAnimationFrame = window[vendors[x]+'RequestAnimationFrame'];
            window.cancelAnimationFrame = window[vendors[x]+'CancelAnimationFrame'] || window[vendors[x]+'CancelRequestAnimationFrame'];
        }

        if(!window.requestAnimationFrame){
            window.requestAnimationFrame = function(callback, element){
                var currTime = new Date().getTime();
                var timeToCall = Math.max(0, 16 - (currTime - lastTime));
                var id = window.setTimeout(function() { callback(currTime + timeToCall); }, timeToCall);
                lastTime = currTime + timeToCall;
                return id;
            };
        }

        if (!window.cancelAnimationFrame){
            window.cancelAnimationFrame = function(id){
                clearTimeout(id);
            };
        }
    };

    if(isCanvasSupported()){
        var c = document.getElementById('waterfall');
        var cw = c.width = 100;
        var ch = c.height = 140;
        var waterfall = new waterfallCanvas(c, cw, ch);
        // setupRAF();
        waterfall.init();
    }
}

function drawCanvasSun(){
    var sun = new Image();
    var moon = new Image();
    var earth = new Image();
    function init() {
        sun.src = 'https://mdn.mozillademos.org/files/1456/Canvas_sun.png';
        moon.src = 'https://mdn.mozillademos.org/files/1443/Canvas_moon.png';
        earth.src = 'https://mdn.mozillademos.org/files/1429/Canvas_earth.png';
        window.requestAnimationFrame(draw);
    }

    function draw() {
        var ctx = document.getElementById('canvas_sun').getContext('2d');

        ctx.globalCompositeOperation = 'destination-over';
        ctx.clearRect(0, 0, 300, 300); // clear canvas

        ctx.fillStyle = 'rgba(0, 0, 0, 0.4)';
        ctx.strokeStyle = 'rgba(0, 153, 255, 0.4)';
        ctx.save();
        ctx.translate(150, 150);

        // Earth
        var time = new Date();
        ctx.rotate(((2 * Math.PI) / 60) * time.getSeconds() + ((2 * Math.PI) / 60000) * time.getMilliseconds());
        ctx.translate(105, 0);
        ctx.fillRect(0, -12, 50, 24); // Shadow
        ctx.drawImage(earth, -12, -12);

        // Moon
        ctx.save();
        ctx.rotate(((2 * Math.PI) / 6) * time.getSeconds() + ((2 * Math.PI) / 6000) * time.getMilliseconds());
        ctx.translate(0, 28.5);
        ctx.drawImage(moon, -3.5, -3.5);
        ctx.restore();

        ctx.restore();

        ctx.beginPath();
        ctx.arc(150, 150, 105, 0, Math.PI * 2, false); // Earth orbit
        ctx.stroke();

        ctx.drawImage(sun, 0, 0, 300, 300);

        window.requestAnimationFrame(draw);
    }

    init();
}

function BubbleSort(divAround){
    this.divAround = divAround;
}

/**
 * https://developer.mozilla.org/en-US/docs/Web/API/Document
 */
BubbleSort.prototype.render = function(){
    this.container = document.createElement('canvas');
    this.container.className = 'bubble';
    this.container.width = this.divAround.width();
    this.container.height = this.divAround.height();

    this.divAround.append(this.container);

    this.initItem();
    this.draw();
};

BubbleSort.prototype.initItem = function(){
    this.maxIndex = 5;
    this.radius = 20;
    this.y_line = 40;
    this.y_change = 150;
    this.between = parseInt((this.container.width - this.radius * 2 * this.maxIndex) / (this.maxIndex + 2));
    this.arrayValue = {};
    for(var i = 0 ; i < this.maxIndex + 1 ; i++) {
        var newItem = new Item(this.container);
        var randomNumber = Math.floor((Math.random() * 100) + 1);
        newItem.initData(randomNumber, this.between * (i+1) + this.radius * ((2 * i) + 1) - parseInt(this.between /2), this.y_line, this.radius);
        this.arrayValue[i]= newItem;
    }
    this.status = 'sort';

    this.leftPositionCx = parseInt(this.container.width / 3);
    this.leftPositionCy = this.y_change;

    this.rightPositionCx = parseInt((this.container.width / 3)*2);
    this.rightPositionCy = this.y_change;

    this.current_i = 0;
    this.current_j = 0;
};

BubbleSort.prototype.clearCanvas = function(){
    this.container.getContext('2d').globalCompositeOperation = 'destination-over';
    this.container.getContext('2d').clearRect(0, 0, this.container.width, this.container.height); // clear canvas
};

BubbleSort.prototype.renderItem = function(){
    for(var i = 0 ; i < this.maxIndex + 1 ; i++) {
        this.arrayValue[i].render();
    }
};

BubbleSort.prototype.sort = function(){
  if(this.status == 'sort'){
      this.current_j = this.current_j + 1;
      if(this.maxIndex < this.current_j){
          this.current_i = this.current_i + 1;
          this.current_j = this.current_i + 1;
      }
      if(this.current_i >= this.maxIndex){
          this.status = 'stop';
      }else{
          this.status = 'changePos';
      }
  }
};

BubbleSort.prototype.changePosition = function(){
    if(this.status == 'changePos'){
        var item_i = this.arrayValue[this.current_i];
        var item_j = this.arrayValue[this.current_j];

        item_i.moveToPosition(this.leftPositionCx, this.leftPositionCy);
        item_j.moveToPosition(this.rightPositionCx, this.rightPositionCy);

        if(item_i.isPos(this.leftPositionCx, this.leftPositionCy) && item_j.isPos(this.rightPositionCx, this.rightPositionCy)){
            this.status = 'compareProcess';
        }

        this.arrayValue[this.current_i] = item_i;
        this.arrayValue[this.current_j] = item_j;
    }else if(this.status == 'compareProcess'){
        var item_i = this.arrayValue[this.current_i];
        var item_j = this.arrayValue[this.current_j];

        var value_of_i = item_i.getNumber();
        var value_of_j = item_j.getNumber();

        if(value_of_i > value_of_j){
            var t_x = item_i.getTempTargetX();
            var t_y = item_i.getTempTargetY();
            item_i.setNextTarget(item_j.getTempTargetX(), item_j.getTempTargetY());
            item_j.setNextTarget(t_x, t_y);
        }else{
            item_i.setNextTarget(item_i.getTempTargetX(), item_i.getTempTargetY());
            item_j.setNextTarget(item_j.getTempTargetX(), item_j.getTempTargetY());
        }

        this.status = 'goBackProcess';

        this.arrayValue[this.current_i] = item_i;
        this.arrayValue[this.current_j] = item_j;
    }else if(this.status == 'goBackProcess'){
        var item_i = this.arrayValue[this.current_i];
        var item_j = this.arrayValue[this.current_j];

        item_i.moveToPosition(item_i.getTempTargetX(), item_i.getTempTargetY());
        item_j.moveToPosition(item_j.getTempTargetX(), item_j.getTempTargetY());

        if(item_i.isPos(item_i.getTempTargetX(), item_i.getTempTargetY()) && item_j.isPos(item_j.getTempTargetX(), item_j.getTempTargetY())){
            this.status = 'changeIndexProcess';
        }

        this.arrayValue[this.current_i] = item_i;
        this.arrayValue[this.current_j] = item_j;
    }else if(this.status == 'changeIndexProcess'){
        var item_i = this.arrayValue[this.current_i];
        var item_j = this.arrayValue[this.current_j];

        var value_of_i = item_i.getNumber();
        var value_of_j = item_j.getNumber();

        if(value_of_i > value_of_j){
            this.arrayValue[this.current_i] = item_j;
            this.arrayValue[this.current_j] = item_i;
        }
        this.status = 'sort';
    }
};

BubbleSort.prototype.draw = function(){
    var me = this;
    this.clearCanvas();
    this.sort();
    this.changePosition();
    this.renderItem();
    if(this.status != 'stop') {
        window.requestAnimationFrame(function () {
            me.draw()
        });
    }
};

function Item(parent){
    this.parent = parent;
    this.ctx = this.parent.getContext("2d");
}

Item.prototype.initData = function(number, centerX, centerY, radius){
    this.centerX = parseInt(centerX);
    this.tempTargetX = parseInt(centerX);
    this.centerY = parseInt(centerY);
    this.tempTargetY = parseInt(centerY);
    this.number = number;
    this.radius = radius;
};

Item.prototype.getNumber = function(){
  return this.number;
};

Item.prototype.reInitData = function(centerX, centerY){
    this.centerX = parseInt(centerX);
    this.centerY = parseInt(centerY);
};

Item.prototype.getCenterX = function(){
  return this.centerX;
};

Item.prototype.setCenterX = function(centerX){
    this.centerX = parseInt(centerX);
};

Item.prototype.getCenterY = function(){
    return this.centerY;
};

Item.prototype.setCenterY = function(centerY){
    this.centerY = parseInt(centerY);
};

Item.prototype.setNextTarget = function(nextCenterX, nextCenterY){
    this.tempTargetX = parseInt(nextCenterX);
    this.tempTargetY = parseInt(nextCenterY);
};

Item.prototype.getTempTargetX = function(){
    return this.tempTargetX;
};

Item.prototype.getTempTargetY = function(){
    return this.tempTargetY;
};

Item.prototype.render = function(){
    this.ctx.beginPath();
    this.ctx.arc(this.centerX, this.centerY, this.radius, 0, 2*Math.PI);
    this.ctx.stroke();

    this.ctx.font = '20px serif';
    this.ctx.fillStyle = "red";
    this.ctx.fillText(this.number, this.centerX - 10, this.centerY + 5);
};

Item.prototype.moveToPosition = function(pos_x, pos_y){
    var valueChange = 1;
    this.centerX = this.moveValueToPosition(this.centerX, parseInt(pos_x), valueChange);
    this.centerY = this.moveValueToPosition(this.centerY, parseInt(pos_y), valueChange);
};

Item.prototype.moveValueToPosition = function(val, pos_val, valueIncrease){
    pos_val = parseInt(pos_val);
    val = parseInt(val);
    if(val < pos_val){
        val = val + valueIncrease;
        if(val > pos_val){
            val = pos_val;
        }
    }else if (val > pos_val){
        val = val - valueIncrease;
        if(val < pos_val){
            val = pos_val;
        }
    }
    return val;
};

Item.prototype.isPos = function(pos_x, pos_y){
    if(this.centerX == parseInt(pos_x) && this.centerY == parseInt(pos_y)){
        return true;
    }
    return false;
};
