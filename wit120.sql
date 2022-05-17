-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: wit120
-- ------------------------------------------------------
-- Server version	8.0.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `case_history`
--

DROP TABLE IF EXISTS `case_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `case_history` (
  `case_history_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `case_history` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`case_history_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `case_history`
--

LOCK TABLES `case_history` WRITE;
/*!40000 ALTER TABLE `case_history` DISABLE KEYS */;
INSERT INTO `case_history` VALUES (4,9,'病历测试\r\n病历测试\r\n病历测试'),(6,14,'头痛\r\n发热'),(7,15,'病例测试\r\n病历测试'),(8,20,'头痛\r\n流涕'),(9,25,'感冒'),(10,29,'感冒、咳嗽，鉴定为流感'),(11,33,'胃痛、反酸'),(12,37,'胃痛'),(13,39,'胃病、胃痛');
/*!40000 ALTER TABLE `case_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `check_record`
--

DROP TABLE IF EXISTS `check_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `check_record` (
  `check_record_id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_id` int(11) NOT NULL,
  `check_record` varchar(1000) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`check_record_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `check_record`
--

LOCK TABLES `check_record` WRITE;
/*!40000 ALTER TABLE `check_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `check_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `check_result`
--

DROP TABLE IF EXISTS `check_result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `check_result` (
  `check_result_id` int(11) NOT NULL AUTO_INCREMENT,
  `med_res_order_id` int(11) NOT NULL,
  `check_result` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`check_result_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `check_result`
--

LOCK TABLES `check_result` WRITE;
/*!40000 ALTER TABLE `check_result` DISABLE KEYS */;
INSERT INTO `check_result` VALUES (1,5,'未发现明显异常'),(2,17,'未发现明显异常'),(3,23,'未见明显异常'),(4,25,'未见明显异常');
/*!40000 ALTER TABLE `check_result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `department_id` int(11) NOT NULL AUTO_INCREMENT,
  `department_name` varchar(45) NOT NULL,
  `department_desc` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`department_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'心血管内科','本学科为卫健委临床重点专科及教育部重点学科，包括6个普通病房（主院区4个、光谷和中法院区各1个）、重症监护病房（CCU）、7个导管室（主院区5个、光谷和中法院区各1个）、心功能室、心脏超声检查室及实验室六大部分组成。现有冠心病、心律失常、高血压、心力衰竭、心脏康复等5个亚专科。实验室现位于中法新城院区科研大楼，占地面积达1500多平方米，包括心血管病遗传与分子机制湖北省重点实验室、电生理实验室、基因诊断及遗传学研究技术平台、代谢组平台、生物样本库等多个部分。目前拥有高血压病临床医学研究中心的。'),(2,'血液内科','华中科技大学同济医学院附属同济医院血液内科历史悠久，创建于1959年，是全国最早成立的血液专科之一。1994年成为临床博士学位点，1998年成为湖北省级重点学科，2000年成为临床血液病学博士后流动站，2004 年为中国造血干细胞捐献者资料库移植、采集定点医院，2007年成为国家级重点学科，2011年成为国家级临床重点专科，2017年成为湖北省血液免疫细胞治疗临床医学研究中心的依托单位。目前血液内科有医技人员74人，其中正、副教授22名，主治医师13名，其中50人具有博士学位。现如今血液专科分布在本部、光谷、中法新城三个院区，共有5个普通病区和1个造血干细胞移植病区（30张床位），开设近300张病床。每年诊治包括白血病、淋巴瘤、骨髓瘤、骨髓增生异常综合征、再生障碍性贫血在内的各类血液病患者数万名；实施各类造血干细胞移植术超过300例；输注嵌合抗原受体T细胞治疗淋巴和浆细胞肿瘤近200例。'),(3,'风湿免疫内科','学科技术力量雄厚，解决了大量风湿免疫病疑难重症的诊治问题，诸如IgG4相关性疾病、干燥综合征、重症系统性红斑狼疮、自身免疫病相关嗜血综合症、免疫相关血栓性血小板减少性紫癜、结缔组织病相关肺间质病和/或肺动脉高压，多发性肌炎/皮肌炎、类风湿关节炎、脊柱关节病、强直性脊柱炎、银屑病关节炎、成人still病、痛风、骨质疏松、系统性硬化症、骨性关节炎病，各种血管炎、疑难关节炎等，各种常见及疑难重症风湿免疫病。其中，对干燥综合征、重症系统性红斑狼疮、自身免疫病相关嗜血综合症、免疫相关血栓性血小板减少性紫癜、结缔组织病相关肺间质病和/或肺动脉高压，多发性肌炎/皮肌炎、类风湿关节炎、脊柱关节病、强直性脊柱炎、银屑病关节炎等各种风湿免疫病的诊治积累了丰富的经验，IgG4相关性疾病的诊治水平已走在国际领先行列。'),(4,'骨科','同济医院骨科创立于1946年，是集医疗、教学、科研三位一体，在国内外具有很高医疗和学术声誉的骨科疾病诊疗中心。同济医院骨科是国家临床重点专科、湖北省骨科医疗质量控制中心。目前骨科主任和学术带头人是李锋教授。本学科设有脊柱外科、关节外科、创伤骨科、骨肿瘤、运动医学、手外科和足踝外科七个亚专科。拥有1个实验中心(骨科研究所)，2个临床中心（湖北省脊柱外科临床医学研究中心、武汉市关节置换临床医学研究中心）。2000年以来，骨科共承担国家级、省部级、市级、校院级等科研项目178项，累计科研经费逾7000万元人民币。骨科现有7个病区330张病床。骨科还承办了一本骨科专业杂志——《骨科》杂志，面向国内外公开发行，是中国科技论文统计源期刊、中国科技核心期刊。'),(5,'胸外科','武汉同济医院胸外科由裘法祖教授和陈夏丰教授六十年代创立，目前由中国科学院赫捷院士指引，付向宁主任带领的专业从事肺、食管、纵隔、胸壁和胸外科救治的科室。目前是国家临床重点专科、全国食管癌临床研究分中心、食管疾病多学科研究平台（世界食管研究组织、美国斯坦福大学、中国国家癌症中心、武汉同济医院胸外科）、首批卫生部胸外科临床路径起草单位、国家癌症质量控制中心湖北分中心、湖北省胸外科医疗质量控制中心、全国首批胸外科医疗随访数据库创建单位、全国首批建立肺癌生物标本库单位、湖北省唯一具有基因检测资质的胸外科实验室、华中地区最大的胸外科治疗中心、全国先进的胸外科疑难杂症治疗基地、湖北省委胸外科干部保健基地。'),(6,'胆胰外科','华中科技大学附属同济医院胆胰外科及腔镜外科中心是专门从事胆道和胰腺疾病诊断及治疗的专科，是同济医院“肝胆胰研究所”和“腔镜外科诊治中心”的重要组成部分。现有开放床位86张，年手术量1500余台次。胆胰外科师资力量雄厚，现有正教授6人，副教授4名，有多位博士学位获得者及归国留学人员。能胜任各种胆道和胰腺疾病的诊治，尤其在复杂和疑难病例的诊断及手术综合治疗方面成绩卓著，社会反响良好。1947年始自裘法祖教授任外科主任主持外科工作后，就开展了“胆囊切除术”、“胆总管切开取石引流术”等；1951年裘法祖教授在全国率先开展并报告“胆总管十二指肠吻合术治疗胆石症”等，推动了中国胆道外科的发展；1958年率先在全国施行一期“胰十二指肠切除术治疗壶腹部周围癌”；1963年开展胰岛细胞瘤的外科治疗，至今仍居国内领先地位。'),(7,'创伤外科','同济医院创伤外科成立于1990年，是以严重多发伤及创伤危重症救治为特色的大型综合创伤急救中心，现拥有外科学博士10人，博士后3人；教授、博士生导师2人；主任医师4人；副教授、硕士生导师9人，大多数从欧美留学回国。创伤外科现拥有病床76张（创伤复苏3张、创伤留观15张、创伤ICU 10张、创伤病房49张），是严格按美国创伤急救模式组建的包含脑外科、普外科、胸外科、血管外科、骨科、外科危重症监护、创伤康复等诸多专业的综合型专业化的大型创伤救治中心，并开通了以院前急救→急诊外科→手术室→创伤ICU→创伤外科病房救治一体化的快速综合创伤急救体系。目前可招收创伤外科及运动医学的博士、硕士研究生，承担了急诊医学、创伤外科学、运动医学的研究生、本科生的教学工作。'),(8,'感染科','感染科下设重症感染病（ICU）、普通感染病、病毒性肝炎、免疫缺陷感染病、肺外结核病、中西医结合感染病等6个亚专科。门诊设有感染病门诊、发热门诊、肠道门诊、免疫缺陷感染（含HIV感染）门诊、丙肝门诊、疑难肝病门诊及肝硬化门诊等7个专病门诊。临床部共5个病区，分别为1个感染病ICU及4个普通病区，并下设人工肝治疗中心、血液净化中心、感染病影像中心等。感染性疾病研究所下设感染免疫研究室和病原学实验室。本学科现有正高级职称医生10人，副高级职称医生12人，博士生导师7人，硕士生导师12人。其中：科技部973传染病重大专项首席科学家1人，卫生部有突出贡献中青年专家1人，国家杰出青年科学基金项目1人，国家“百千万”人才工程1人，国务院特殊津贴获得者1人，湖北省新世纪高层次人才工程人选1人，湖北省医学领军人才1人。'),(9,'消化内科','同济医院消化内科及消化内镜中心由我国已故消化内科的先驱，著名肝脏病学专家过晋源教授创建于上个世纪50年代。目前我学科已建设成为集医疗、教学、科研和培训为一体，以肝病、炎症性肠病和内镜诊疗为特色，在全国有重要影响力的专科。消化内科拥有一支高素质的医疗技术队伍，现有工作人员200余名，其中中华医学会消化病学分会常委1人，中华医学会消化内镜分会委员1人，湖北省医学会消化内镜分会主任委员1人，教育部青年长江学者1人，国家自然科学基金优秀青年基金获得者1人；20余名专家在全国及省市医学会消化及消化内镜分会及多种学术期刊任职。消化内科在省内率先设置重症监护病区，另有肝脏病区、胆胰病区、胃肠病区及日间病房、住院及门诊消化内镜中心、胃肠动力检查室等单元；光谷院区及中法新城院区各有1个消化病区和1个消化内镜中心。我科建立了完善的三级医师查房制度和疑难病例会诊讨论制度，专病专治，对病人的收治更加细化，体现专科特色，同时配备有各种监护与抢救设备，确保高质量的医疗服务，重症监护病区设备设施先进完善。'),(10,'内分泌内科','我科在省内率先开展多项新技术，包括CSII应用于院内高血糖管理、甲状腺机能亢进的介人治疗、糖尿病高渗性昏迷的血液净化疗法、糖尿病和肥胖手术治疗、糖尿病足早期检测和干预、糖尿病足的综合治疗、糖尿病足血小板凝胶治疗、甲状腺结节分子诊断、双侧肾上腺采血分型原发性醛固酮增多症、岩下窦采血分型Cushing综合症、分段钙刺激后采血定位胰岛细胞瘤、胃复安试验分型高泌乳素血症、难治性甲状腺眼病治疗、甲状腺和甲状旁腺消融、多种罕见内分泌代谢遗传病的基因诊断。');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doc_info`
--

DROP TABLE IF EXISTS `doc_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doc_info` (
  `doc_info_id` int(11) NOT NULL AUTO_INCREMENT,
  `doc_id` int(11) NOT NULL,
  `doc_name` varchar(45) DEFAULT NULL,
  `department_id` int(11) DEFAULT NULL,
  `level` varchar(45) DEFAULT '医师',
  `doc_desc` varchar(1000) DEFAULT '暂无',
  `identification_num` varchar(45) DEFAULT NULL,
  `gender` tinyint(1) DEFAULT '1',
  `age` int(11) DEFAULT '0',
  PRIMARY KEY (`doc_info_id`),
  UNIQUE KEY `doc_id_UNIQUE` (`doc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doc_info`
--

LOCK TABLES `doc_info` WRITE;
/*!40000 ALTER TABLE `doc_info` DISABLE KEYS */;
INSERT INTO `doc_info` VALUES (1,1,'王大平',2,'副主任医师','医生简介123456','420683199907281212',1,54),(2,2,'田德安',1,'副主任医师','暂无','420683199907281218',1,55),(3,11,'徐永健',4,'副主任医师','暂无','420683199907281218',1,57),(4,12,'张珍祥',3,'主治医师','暂无','420683199907271218',1,46),(5,13,'熊盛道',1,'主任医师','暂无','420683199907281218',1,54),(7,15,'王晓旭',4,'副主任医师','暂无','420683197602311218',1,55),(8,16,'魏岳阳',2,'主任医师','暂无','1312312312312312321',1,25),(13,35,'马烨鑫',1,'主任医师','暂无','420681196507281216',1,57);
/*!40000 ALTER TABLE `doc_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor_concise_shift_info`
--

DROP TABLE IF EXISTS `doctor_concise_shift_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctor_concise_shift_info` (
  `concise_info_id` int(11) NOT NULL AUTO_INCREMENT,
  `doctor_id` int(11) NOT NULL,
  `day` int(11) NOT NULL,
  `noon` int(11) NOT NULL,
  PRIMARY KEY (`concise_info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor_concise_shift_info`
--

LOCK TABLES `doctor_concise_shift_info` WRITE;
/*!40000 ALTER TABLE `doctor_concise_shift_info` DISABLE KEYS */;
INSERT INTO `doctor_concise_shift_info` VALUES (1,1,2,1),(2,2,3,2),(3,1,4,2),(4,1,5,1),(5,2,4,1),(6,2,2,1),(8,13,1,1),(9,13,0,2),(12,16,3,1),(13,16,4,2),(16,13,4,1),(19,35,2,1);
/*!40000 ALTER TABLE `doctor_concise_shift_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor_shift_info`
--

DROP TABLE IF EXISTS `doctor_shift_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctor_shift_info` (
  `doctor_shift_info` int(11) NOT NULL AUTO_INCREMENT,
  `doctor_id` int(11) NOT NULL,
  `day` int(11) NOT NULL,
  `time_slice` int(11) NOT NULL,
  `patients_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`doctor_shift_info`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor_shift_info`
--

LOCK TABLES `doctor_shift_info` WRITE;
/*!40000 ALTER TABLE `doctor_shift_info` DISABLE KEYS */;
INSERT INTO `doctor_shift_info` VALUES (1,1,2,3,1),(2,2,3,4,0),(3,1,2,1,0),(4,1,2,2,0),(5,1,4,4,0),(6,1,4,5,0),(7,1,4,6,0),(8,1,5,1,0),(9,1,5,2,0),(10,1,5,3,0),(11,2,3,5,1),(12,2,3,6,0),(13,2,4,1,0),(14,2,4,2,0),(15,2,4,3,1),(16,2,2,1,0),(17,2,2,2,0),(18,2,2,3,0),(22,13,1,1,0),(23,13,1,2,0),(24,13,1,3,0),(25,13,0,4,1),(26,13,0,5,0),(27,13,0,6,0),(31,16,3,1,0),(32,16,3,2,0),(33,16,3,3,0),(34,16,4,4,0),(35,16,4,5,0),(36,16,4,6,0),(43,13,4,1,1),(44,13,4,2,1),(45,13,4,3,0),(52,35,2,1,0),(53,35,2,2,0),(54,35,2,3,0);
/*!40000 ALTER TABLE `doctor_shift_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `drug`
--

DROP TABLE IF EXISTS `drug`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `drug` (
  `drug_id` int(11) NOT NULL AUTO_INCREMENT,
  `drug_name` varchar(45) NOT NULL,
  `approval_num` varchar(45) NOT NULL,
  `formulation` varchar(45) NOT NULL,
  `specification` varchar(45) NOT NULL,
  `manufacturer` varchar(45) NOT NULL,
  `category` varchar(45) NOT NULL,
  `cost` double(5,2) NOT NULL,
  PRIMARY KEY (`drug_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drug`
--

LOCK TABLES `drug` WRITE;
/*!40000 ALTER TABLE `drug` DISABLE KEYS */;
INSERT INTO `drug` VALUES (1,'感冒清片','国药准字Z36021176','片剂(薄膜衣)','每素片重0.22g(含对乙酰氨基酚12mg)','江西恒康药业有限公司','中成药',21.15),(2,'阿司匹林维C肠溶片','国药准字H14023604','内服药','阿司匹林0.25g,维生素C25mg','山西威奇达药业有限公司','西药',18.60),(3,'布洛芬缓释胶囊','国药准字H10900089','内服药','10粒','中美史克','西药',13.57),(4,'阿莫西林片','国药准字H23020941','片剂','0.25g(按C16H19N3O5S计)','哈药集团制药总厂','西药',4.60),(5,'999感冒灵颗粒','国药准字Z44021940','颗粒剂','10克/9袋','三九医药股份有限公司','中成药',9.80),(6,'红霉素软膏','国药准字H34020379','内服药','1%','安徽新和成皖南药业有限公司','西药',0.75),(7,'维生素C泡腾片','国药准字H61022373','内服药','1克/12片','西安利君制药有限责任公司','西药',19.80),(8,'复方氨酚烷胺胶囊','国药准字H46020636','胶囊剂','复方','海南亚洲制药有限公司','西药',8.21),(9,'藿香正气软胶囊','国药准字Z13020797','胶囊剂','0.45克/24粒','神威药业有限公司','中成药',11.00),(10,'连花清瘟胶囊','国药准字Z20040063','胶囊剂','0.35克/24粒','石家庄以岭药业有股份有限公司','中成药',13.40),(11,'香砂六君丸','国药准字Z62021237','内服药','每8丸相当于原生药3g','兰州太宝制药有限公司','中成药',2.50),(12,'正骨水','国药准字Z45021659','内服药','12毫升','广西玉林制药有限责任公司','中成药',9.85);
/*!40000 ALTER TABLE `drug` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medical_resource_order`
--

DROP TABLE IF EXISTS `medical_resource_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medical_resource_order` (
  `med_res_order_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `patient_id` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `day` int(11) NOT NULL,
  `noon` int(11) NOT NULL,
  `cost` int(11) NOT NULL,
  `medical_res_id` int(11) NOT NULL,
  PRIMARY KEY (`med_res_order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medical_resource_order`
--

LOCK TABLES `medical_resource_order` WRITE;
/*!40000 ALTER TABLE `medical_resource_order` DISABLE KEYS */;
INSERT INTO `medical_resource_order` VALUES (5,15,33,'2022-03-25 03:31:59',5,1,200,2),(17,29,10,'2022-03-30 07:35:58',3,1,200,2),(23,37,33,'2022-03-31 01:23:20',4,1,150,4),(25,39,10,'2022-03-31 04:19:15',4,2,150,4);
/*!40000 ALTER TABLE `medical_resource_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medical_technician`
--

DROP TABLE IF EXISTS `medical_technician`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medical_technician` (
  `technician_id` int(11) NOT NULL AUTO_INCREMENT,
  `technician_name` varchar(45) NOT NULL,
  `doc_id` int(11) NOT NULL,
  `cost` int(11) NOT NULL,
  PRIMARY KEY (`technician_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medical_technician`
--

LOCK TABLES `medical_technician` WRITE;
/*!40000 ALTER TABLE `medical_technician` DISABLE KEYS */;
INSERT INTO `medical_technician` VALUES (1,'彩超',20,120),(2,'CT',18,200),(4,'胃镜',17,150),(6,'肠镜',32,300),(7,'B超',25,100),(8,'X光',26,50),(9,'心电图',27,50);
/*!40000 ALTER TABLE `medical_technician` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `my_order`
--

DROP TABLE IF EXISTS `my_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `my_order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_id` int(11) NOT NULL,
  `doctor_id` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `order_day` int(11) NOT NULL,
  `order_time_slice` int(11) NOT NULL,
  `cost` int(11) NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `my_order`
--

LOCK TABLES `my_order` WRITE;
/*!40000 ALTER TABLE `my_order` DISABLE KEYS */;
INSERT INTO `my_order` VALUES (1,5,1,'2022-03-14 08:01:16',2,3,15),(3,5,2,'2022-03-14 08:36:55',3,4,30),(4,6,1,'2022-03-15 04:05:08',2,3,10),(9,10,2,'2022-03-23 07:30:25',3,6,20),(14,33,2,'2022-03-24 02:32:59',4,3,20),(15,33,1,'2022-03-25 01:52:07',5,3,20),(20,33,13,'2022-03-27 14:02:22',0,4,20),(25,10,1,'2022-03-29 07:01:12',2,3,20),(29,10,2,'2022-03-30 06:55:18',3,5,20),(33,10,13,'2022-03-30 16:07:18',4,1,20),(37,33,13,'2022-03-31 01:19:59',4,2,20),(39,10,2,'2022-03-31 04:15:25',4,3,20);
/*!40000 ALTER TABLE `my_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient_info`
--

DROP TABLE IF EXISTS `patient_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient_info` (
  `patient_info_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `real_name` varchar(45) DEFAULT NULL,
  `identification_num` varchar(45) DEFAULT NULL,
  `gender` tinyint(1) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`patient_info_id`),
  KEY `patient_id_idx` (`user_id`),
  CONSTRAINT `patient_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_info`
--

LOCK TABLES `patient_info` WRITE;
/*!40000 ALTER TABLE `patient_info` DISABLE KEYS */;
INSERT INTO `patient_info` VALUES (1,5,'张三','632323190605269281',1,33),(2,6,'王五','232312321321312312',1,32),(5,10,'彦伟平','420683199907291217',0,24),(6,33,'黄金鑫','420683199906231217',1,24);
/*!40000 ALTER TABLE `patient_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prescription`
--

DROP TABLE IF EXISTS `prescription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prescription` (
  `prescription_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `prescription` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`prescription_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prescription`
--

LOCK TABLES `prescription` WRITE;
/*!40000 ALTER TABLE `prescription` DISABLE KEYS */;
INSERT INTO `prescription` VALUES (4,9,'红霉素软膏x2;布洛芬缓释胶囊x1;'),(6,14,'感冒清片x2;999感冒灵颗粒x1;'),(7,15,'阿司匹林维C肠溶片x1;'),(8,20,'红霉素软膏x2;'),(9,25,'感冒清片x3;'),(10,29,'感冒清片x1;阿莫西林片x1;维生素C泡腾片x1;'),(11,33,'连花清瘟胶囊x1;维生素C泡腾片x1;'),(12,37,'999感冒灵颗粒x1;阿莫西林片x2;'),(13,39,'感冒清片x2;999感冒灵颗粒x1;');
/*!40000 ALTER TABLE `prescription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource_recommend`
--

DROP TABLE IF EXISTS `resource_recommend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resource_recommend` (
  `resource_recommend_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `resource_recommend` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`resource_recommend_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource_recommend`
--

LOCK TABLES `resource_recommend` WRITE;
/*!40000 ALTER TABLE `resource_recommend` DISABLE KEYS */;
INSERT INTO `resource_recommend` VALUES (4,9,'CT;胃镜;'),(6,14,'CT;胃镜;'),(7,15,'B超;'),(8,20,'B超;'),(9,25,'CT;B超;'),(10,29,'CT;胃镜;'),(11,33,'胃镜;CT;'),(12,37,'CT;胃镜;'),(13,39,'CT;胃镜;');
/*!40000 ALTER TABLE `resource_recommend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `permission` int(11) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'wangping','123456789','12345678901',2),(2,'tiandean','123456789','12345678902',2),(5,'张三','Jeffisverycool11','12345678903',1),(6,'王五','123456789','12345678912',1),(10,'865150663','123456789','18972093939',1),(11,'xuyongjian','123456789','12345678912',2),(12,'zhangzhenxiang','123456789','12345678913',2),(13,'xiongshengdao','123456789','12345678914',2),(15,'huangjinx','hjx1999617','17862933442`',2),(16,'weiyueyang','123456789','12345328901',2),(17,'liubin','123456789','12312312331',4),(18,'yaoqiaori','123456789','12451252131',4),(19,'huhuhu','123456789','14123412331',4),(20,'fafawfaw','123456789','44412441231',4),(24,'admin','123456789','18765431552',3),(25,'yijiyisheng1','123456789','21414122311',4),(26,'yijiyisheng2','123456789','41512315341',4),(27,'yijiyisheng3','123456789','51412351231',4),(28,'yijiyisheng4','123456789','41151431212',4),(29,'yijiyisheng5','123456789','51412451235',4),(30,'yijiyisheng6','123456789','51462312512',4),(31,'yijiyisheng7','123456789','51546241251',4),(32,'yijiyisheng8','123456789','51252315134',4),(33,'1073130610','123456789','41541441523',1),(35,'mayexin','123456789','18995327581',2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `verification_code`
--

DROP TABLE IF EXISTS `verification_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `verification_code` (
  `phone` varchar(45) NOT NULL,
  `code` varchar(45) NOT NULL,
  PRIMARY KEY (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verification_code`
--

LOCK TABLES `verification_code` WRITE;
/*!40000 ALTER TABLE `verification_code` DISABLE KEYS */;
INSERT INTO `verification_code` VALUES ('18972093939','8167');
/*!40000 ALTER TABLE `verification_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ward_info`
--

DROP TABLE IF EXISTS `ward_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ward_info` (
  `ward_info_id` int(11) NOT NULL AUTO_INCREMENT,
  `ward_id` int(11) NOT NULL,
  `bed_id` int(11) NOT NULL,
  `patient_id` int(11) NOT NULL,
  `principal_id` int(11) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ward_info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ward_info`
--

LOCK TABLES `ward_info` WRITE;
/*!40000 ALTER TABLE `ward_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `ward_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-17 11:42:09
