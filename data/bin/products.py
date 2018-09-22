import mysql.connector, csv, os.path, sys, datetime
from os import path

try:

	mydb = mysql.connector.connect(
	  host="localhost",
	  user="root",
	  passwd="admin",
	  database="bikeshop"
	)

	mycursor = mydb.cursor()
	productscsv = "products.csv"

	if path.exists(productscsv):
	
		allData = csv.reader(open(productscsv, 'rt'), delimiter=',', quotechar='"')
		next(allData)
		for row in allData:
			val = (row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7], row[8], row[9], row[10], row[11], row[12], datetime.datetime.today().strftime('%Y-%m-%d-%H-%M-%S'))
			sql = "INSERT INTO products (sku, name, description_short, description_long, description_size, price, price_net, vat, image_main, image_second, image_third, category_id, manufacturer_id, date_added) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)"

			mycursor.execute(sql, val)
			mydb.commit()
		
		print('Products added successfully!')
			
	else:
		print('Products file not exist. Nothing added')
		

except Exception as err:
	sys.stderr.write('ERROR: %s' % str(err))
