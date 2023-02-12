

# Run the Application

You will find the [Jenkins](https://github.com/azrindipu/vimondPipeline) pipeline for this application. The pipeline will call the ansible. So you need the ansible too. You can find the ansible script in [this](https://github.com/azrindipu/vimondAnsible) repo for this application. You have to modify the [inventory](https://github.com/azrindipu/vimondAnsible/blob/main/inventory/servers.txt) informations which can be found in the ansible repo. Make sure that the user, you are using in the inventory, doest not require authenticate to SSH remote machine. You can use the following command to make the user auth free access.

```
sudo visudo
%YOUR_INVENTORY_USER   ALL=(ALL:ALL) NOPASSWD: ALL
```

After successful pipeline execution, you can access the [swagger](http://localhost:8280/app/swagger-ui.html). You can check the status of the application using following command in the remote machine.

```
sudo systemctl status vimond.service
sudo systemctl start vimond.service
sudo systemctl stop vimond.service
```

