const { app, BrowserWindow } = require('electron')

app.whenReady().then(() => {
    createWindow()
})

const createWindow = () => {
    const win = new BrowserWindow({
        width: 800,
        height: 600
    })

    win.loadFile('index.html')
    //Ah, so win here is the new router. or maybe i can route stuff on top of it. feed it a var that i change to?

    app.on('window-all-closed', () => {
        if (process.platform !== 'darwin') app.quit()
    })
}