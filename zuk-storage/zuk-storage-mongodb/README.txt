基于 MongoDB 的文件服务器。
MongoDB File Server 致力于小型文件的存储，比如博客中图片、普通文档等。
由于MongoDB 支持多种数据格式的存储，对于二进制的存储自然也是不话下，所以可以很方便的用于存储文件。
由于 MongoDB 的 BSON 文档对于数据量大小的限制（每个文档不超过16M），所以本文件服务器主要针对的是小型文件的存储。
对于大型文件的存储（比如超过16M），MongoDB 官方已经提供了成熟的产品 GridFS，读者朋友可以自行了解。