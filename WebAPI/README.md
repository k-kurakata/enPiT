# BuddyBot Web API

This is the BuddyBot web API includes RSNP

## Installation

If you use Vagrant, add this sentence to your `Vagrantfile`.

	config.vm.network :forwarded_port, guest:3000, host:3000

Then simply clone this repository to your VM by following:

	$ git clone https://github.com/2014-enPiT-Robot/WebAPI.git

Then run following commands:

	$ cd WebAPI
	$ bundle install
	$ bundle exec rake db:migrate

Now you can run the server.

	$ rails server

Access `localhost:3000` by an web browser.

Enjoy BuddyBot!
