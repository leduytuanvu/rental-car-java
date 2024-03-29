USE [master]
GO
/****** Object:  Database [Assignment3_LeDuyTuanVu]    Script Date: 3/7/2021 6:18:34 PM ******/
CREATE DATABASE [Assignment3_LeDuyTuanVu]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Assignment3_LeDuyTuanVu', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\Assignment3_LeDuyTuanVu.mdf' , SIZE = 3264KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'Assignment3_LeDuyTuanVu_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\Assignment3_LeDuyTuanVu_log.ldf' , SIZE = 832KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [Assignment3_LeDuyTuanVu] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Assignment3_LeDuyTuanVu].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Assignment3_LeDuyTuanVu] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Assignment3_LeDuyTuanVu] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Assignment3_LeDuyTuanVu] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Assignment3_LeDuyTuanVu] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Assignment3_LeDuyTuanVu] SET ARITHABORT OFF 
GO
ALTER DATABASE [Assignment3_LeDuyTuanVu] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [Assignment3_LeDuyTuanVu] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Assignment3_LeDuyTuanVu] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Assignment3_LeDuyTuanVu] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Assignment3_LeDuyTuanVu] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Assignment3_LeDuyTuanVu] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Assignment3_LeDuyTuanVu] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Assignment3_LeDuyTuanVu] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Assignment3_LeDuyTuanVu] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Assignment3_LeDuyTuanVu] SET  ENABLE_BROKER 
GO
ALTER DATABASE [Assignment3_LeDuyTuanVu] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Assignment3_LeDuyTuanVu] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Assignment3_LeDuyTuanVu] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Assignment3_LeDuyTuanVu] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Assignment3_LeDuyTuanVu] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Assignment3_LeDuyTuanVu] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Assignment3_LeDuyTuanVu] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Assignment3_LeDuyTuanVu] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [Assignment3_LeDuyTuanVu] SET  MULTI_USER 
GO
ALTER DATABASE [Assignment3_LeDuyTuanVu] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Assignment3_LeDuyTuanVu] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Assignment3_LeDuyTuanVu] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Assignment3_LeDuyTuanVu] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [Assignment3_LeDuyTuanVu] SET DELAYED_DURABILITY = DISABLED 
GO
USE [Assignment3_LeDuyTuanVu]
GO
/****** Object:  Table [dbo].[tblCars]    Script Date: 3/7/2021 6:18:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblCars](
	[carID] [varchar](50) NOT NULL,
	[name] [varchar](1000) NULL,
	[color] [varchar](50) NULL,
	[year] [varchar](10) NULL,
	[price] [float] NULL,
	[quantity] [int] NULL,
	[image] [varchar](8000) NULL,
	[description] [varchar](8000) NULL,
	[typeID] [varchar](50) NULL,
	[createDate] [date] NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[carID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblDiscounts]    Script Date: 3/7/2021 6:18:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblDiscounts](
	[discountID] [varchar](10) NOT NULL,
	[discountName] [varchar](50) NULL,
	[value] [float] NULL,
	[status] [bit] NULL,
	[dateStart] [date] NULL,
	[dateEnd] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[discountID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblOrderDetails]    Script Date: 3/7/2021 6:18:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblOrderDetails](
	[carID] [varchar](50) NOT NULL,
	[orderID] [varchar](50) NOT NULL,
	[price] [float] NULL,
	[quantity] [int] NULL,
	[dateHire] [date] NOT NULL,
	[dateReturn] [date] NOT NULL,
	[numberDate] [int] NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[carID] ASC,
	[orderID] ASC,
	[dateHire] ASC,
	[dateReturn] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblOrders]    Script Date: 3/7/2021 6:18:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblOrders](
	[orderID] [varchar](50) NOT NULL,
	[total] [float] NULL,
	[dateOrder] [date] NULL,
	[userID] [varchar](50) NULL,
	[discount] [varchar](50) NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[orderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblRoles]    Script Date: 3/7/2021 6:18:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblRoles](
	[roleID] [varchar](50) NOT NULL,
	[roleName] [varchar](50) NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[roleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblTypeCars]    Script Date: 3/7/2021 6:18:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblTypeCars](
	[typeID] [varchar](50) NOT NULL,
	[typeName] [varchar](50) NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[typeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblUsers]    Script Date: 3/7/2021 6:18:34 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblUsers](
	[userID] [varchar](50) NOT NULL,
	[fullName] [varchar](50) NULL,
	[password] [varchar](50) NULL,
	[phone] [varchar](15) NULL,
	[email] [varchar](50) NULL,
	[address] [varchar](500) NULL,
	[roleID] [varchar](50) NULL,
	[codeActive] [varchar](5) NULL,
	[active] [bit] NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car1', N'BMW 740', N'Black', N'2018', 86, 5, N'https://www.cstatic-images.com/car-pictures/main/usd00bmc081a021001.png', N'This is a modern car', N'type1', CAST(N'2021-02-01' AS Date), 1)
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car10', N'Audi S8', N'Black', N'2021', 13, 2, N'https://www.cstatic-images.com/car-pictures/main/usd00auc131a021001.png', N'This is a modern car', N'type3', CAST(N'2021-02-02' AS Date), 1)
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car11', N'BMW 228 Gran Coupe', N'While', N'2020', 75, 3, N'https://www.cstatic-images.com/car-pictures/main/usd00bmc951a021001.png', N'This is a modern car', N'type1', CAST(N'2021-02-03' AS Date), 1)
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car12', N'BMW 330', N'Red', N'2021', 85, 6, N'https://www.cstatic-images.com/car-pictures/main/usc90bmc223a021001.png', N'This is a modern car', N'type1', CAST(N'2021-02-01' AS Date), 1)
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car13', N'BMW 530', N'While', N'2019', 95, 4, N'https://www.cstatic-images.com/car-pictures/main/usd10bmc101a021001.png', N'This is a modern car', N'type1', CAST(N'2021-02-02' AS Date), 1)
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car14', N'Tesla Model 3', N'While', N'2021', 37, 8, N'https://www.cstatic-images.com/car-pictures/main/usc80tsc032a021001.png', N'This is a modern car', N'type2', CAST(N'2021-02-22' AS Date), 1)
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car15', N'Tesla Model S', N'While ', N'2021', 69, 3, N'https://www.cstatic-images.com/car-pictures/main/usc70tsc024b021001.png', N'This is a modern car', N'type2', CAST(N'2021-02-21' AS Date), 1)
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car16', N'BMW 530e', N'Black', N'2021', 57, 3, N'https://www.cstatic-images.com/car-pictures/main/usd10bmc832a021001.png', N'This is a modern car', N'type1', CAST(N'2021-02-20' AS Date), 1)
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car17', N'BMW 450', N'While', N'2021', 59, 7, N'https://www.cstatic-images.com/car-pictures/main/usd10bmc101a021001_2.png', N'This is a modern car', N'type1', CAST(N'2021-02-19' AS Date), 1)
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car18', N'BMW 745e', N'Black', N'2021', 95, 9, N'https://www.cstatic-images.com/car-pictures/main/usd00bmc901a021001.png', N'This is a modern car', N'type1', CAST(N'2021-02-12' AS Date), 1)
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car19', N'BMW 840 Gran Coupe', N'Gray', N'2021', 85, 2, N'https://www.cstatic-images.com/car-pictures/main/usd00bmc921a021001.png', N'This is a modern car', N'type1', CAST(N'2021-02-01' AS Date), 1)
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car2', N'BMW 750', N'While', N'2017', 10, 4, N'https://www.cstatic-images.com/car-pictures/main/usd00bmc012a021001.png', N'This is a modern car', N'type1', CAST(N'2021-02-05' AS Date), 1)
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car20', N'BMW ALPINA B7', N'Black', N'2021', 14, 5, N'https://www.cstatic-images.com/car-pictures/main/usd00bmc321a021001.png', N'This is a modern car', N'type1', CAST(N'2021-02-04' AS Date), 1)
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car21', N'BMW M235 Gran Coupe', N'While', N'2021', 45, 5, N'https://www.cstatic-images.com/car-pictures/main/usd00bmc961a021001.png', N'This is a modern car', N'type1', CAST(N'2021-02-02' AS Date), 1)
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car22', N'BMW M3', N'Green', N'2021', 69, 3, N'https://www.cstatic-images.com/car-pictures/main/cad10bmc111b021001.png', N'This is a modern car', N'type1', CAST(N'2021-02-03' AS Date), 1)
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car23', N'BMW M340', N'While', N'2021', 5, 3, N'https://www.cstatic-images.com/car-pictures/main/usd00bmc891a021001.png', N'This is a modern car', N'type1', CAST(N'2021-02-04' AS Date), 1)
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car24', N'BMW M5', N'Gray', N'2021', 10, 4, N'https://www.cstatic-images.com/car-pictures/main/usd10bmc171a021001.png', N'This is a modern car', N'type1', CAST(N'2021-02-14' AS Date), 1)
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car25', N'BMW M550', N'Gray', N'2021', 76, 8, N'https://www.cstatic-images.com/car-pictures/main/usd10bmc841a021001.png', N'This is a modern car', N'type1', CAST(N'2021-02-15' AS Date), 1)
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car26', N'BMW M760', N'Black', N'2021', 15, 9, N'https://www.cstatic-images.com/car-pictures/main/usd00bmc821a021001.png', N'This is a modern car', N'type1', CAST(N'2021-02-13' AS Date), 1)
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car27', N'BMW M8 Gran Coupe', N'Black', N'2021', 13, 1, N'https://www.cstatic-images.com/car-pictures/main/usd00bmc941a021001.png', N'This is a modern car', N'type1', CAST(N'2021-02-11' AS Date), 1)
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car28', N'BMW M850 Gran Coupe', N'While', N'2021', 10, 2, N'https://www.cstatic-images.com/car-pictures/main/usd00bmc931a021001.png', N'This is a modern car', N'type1', CAST(N'2021-02-10' AS Date), 1)
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car29', N'Cadillac CT4', N'Black', N'2021', 33, 3, N'https://www.cstatic-images.com/car-pictures/main/usd00cac271b021001.png', N'This is a modern car', N'type4', CAST(N'2021-02-19' AS Date), 1)
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car3', N'Tesla Model 3', N'While', N'2021', 37, 3, N'https://www.cstatic-images.com/car-pictures/main/usc80tsc032a021001.png', N'This is a modern car', N'type2', CAST(N'2021-02-09' AS Date), 1)
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car30', N'Cadillac CT5', N'While', N'2021', 36, 4, N'https://www.cstatic-images.com/car-pictures/main/usd00cac261c021001.png', N'This is a modern car', N'type4', CAST(N'2021-02-11' AS Date), 1)
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car31', N'Honda Accord', N'Black', N'2021', 24, 5, N'https://www.cstatic-images.com/car-pictures/main/usd10hoc011f021001.png', N'This is a modern car', N'type5', CAST(N'2021-02-09' AS Date), 1)
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car32', N'Honda Accord Hybrid', N'Black', N'2021', 26, 1, N'https://www.cstatic-images.com/car-pictures/main/usd10hoc091d021001.png', N'This is a modern car', N'type5', CAST(N'2021-02-09' AS Date), 1)
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car33', N'Honda Clarity', N'Red', N'2021', 33, 5, N'https://www.cstatic-images.com/car-pictures/main/usc90hoc161b021001.png', N'This is a modern car', N'type5', CAST(N'2021-02-27' AS Date), 1)
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car34', N'Honda Insight', N'Black', N'2021', 23, 2, N'https://www.cstatic-images.com/car-pictures/main/usd10hoc061c021001.png', N'This is a modern car', N'type5', CAST(N'2021-02-23' AS Date), 1)
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car35', N'Hyundai Accent', N'Gray', N'2021', 15, 12, N'https://www.cstatic-images.com/car-pictures/main/usd10hyc012b021001.png', N'This is a modern car', N'type6', CAST(N'2021-02-12' AS Date), 1)
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car36', N'Hyundai Elantra', N'Black', N'2021', 19, 2, N'https://www.cstatic-images.com/car-pictures/main/usd10hyc021e021001.png', N'This is a modern car', N'type6', CAST(N'2021-02-28' AS Date), 1)
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car37', N'Hyundai Elantra HEV', N'Black', N'2021', 23, 2, N'https://www.cstatic-images.com/car-pictures/main/usd10hyc191b021001.png', N'This is a modern car', N'type6', CAST(N'2021-02-26' AS Date), 1)
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car38', N'Hyundai Sonata', N'Black', N'2021', 23, 9, N'https://www.cstatic-images.com/car-pictures/main/usd00hyc031d021001.png', N'This is a modern car', N'type6', CAST(N'2021-02-24' AS Date), 1)
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car39', N'Hyundai Sonata Hybrid', N'Red', N'2021', 27, 9, N'https://www.cstatic-images.com/car-pictures/main/usd00hyc151c021001.png', N'This is a modern car', N'type6', CAST(N'2021-02-20' AS Date), 1)
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car4', N'Tesla Model S', N'While', N'2020', 69, 7, N'https://www.cstatic-images.com/car-pictures/main/usc70tsc024b021001.png', N'This is a modern car', N'type2', CAST(N'2021-02-09' AS Date), 1)
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car40', N'Lexus ES 250', N'Black', N'2021', 40, 3, N'https://www.cstatic-images.com/car-pictures/main/usd10lec382a021001.png', N'This is a modern car', N'type7', CAST(N'2021-02-09' AS Date), 1)
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car41', N'Lexus ES 300h', N'While', N'2021', 41, 7, N'https://www.cstatic-images.com/car-pictures/main/usc90lec251a021001.png', N'This is a modern car', N'type7', CAST(N'2021-02-22' AS Date), 1)
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car5', N'Audi A4', N'Brown', N'2021', 39, 6, N'https://www.cstatic-images.com/car-pictures/main/usd00auc016a021001.png', N'This is a modern car', N'type3', CAST(N'2021-02-08' AS Date), 1)
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car6', N'Audi A6', N'While', N'2021', 54, 5, N'https://www.cstatic-images.com/car-pictures/main/usc90auc021a021001.png', N'This is a modern car', N'type3', CAST(N'2021-02-07' AS Date), 1)
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car7', N'Audi A8', N'While', N'2018', 86, 3, N'https://www.cstatic-images.com/car-pictures/main/usd00auc361a021001.png', N'This is a modern car', N'type3', CAST(N'2021-02-06' AS Date), 1)
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car8', N'Audi S4', N'While', N'2021', 49, 1, N'https://www.cstatic-images.com/car-pictures/main/usd00auc084a021001_2.png', N'This is a modern car', N'type3', CAST(N'2021-02-05' AS Date), 1)
INSERT [dbo].[tblCars] ([carID], [name], [color], [year], [price], [quantity], [image], [description], [typeID], [createDate], [status]) VALUES (N'car9', N'Audi S6', N'Black', N'2021', 74, 8, N'https://www.cstatic-images.com/car-pictures/main/usd00auc031a021001.png', N'This is a modern car', N'type3', CAST(N'2021-02-23' AS Date), 1)
INSERT [dbo].[tblDiscounts] ([discountID], [discountName], [value], [status], [dateStart], [dateEnd]) VALUES (N'discount1', N'Discount 10%', 0.1, 1, CAST(N'2021-02-20' AS Date), CAST(N'2021-03-01' AS Date))
INSERT [dbo].[tblDiscounts] ([discountID], [discountName], [value], [status], [dateStart], [dateEnd]) VALUES (N'discount2', N'Discount 20%', 0.2, 1, CAST(N'2021-02-20' AS Date), CAST(N'2021-03-20' AS Date))
INSERT [dbo].[tblDiscounts] ([discountID], [discountName], [value], [status], [dateStart], [dateEnd]) VALUES (N'discount3', N'Discount 30%', 0.3, 1, CAST(N'2021-02-20' AS Date), CAST(N'2021-03-20' AS Date))
INSERT [dbo].[tblDiscounts] ([discountID], [discountName], [value], [status], [dateStart], [dateEnd]) VALUES (N'discount4', N'Discount 40%', 0.4, 1, CAST(N'2021-02-20' AS Date), CAST(N'2021-03-20' AS Date))
INSERT [dbo].[tblDiscounts] ([discountID], [discountName], [value], [status], [dateStart], [dateEnd]) VALUES (N'discount5', N'Discount 50%', 0.5, 1, CAST(N'2021-03-20' AS Date), CAST(N'2021-04-20' AS Date))
INSERT [dbo].[tblDiscounts] ([discountID], [discountName], [value], [status], [dateStart], [dateEnd]) VALUES (N'discount6', N'Discount 60%', 0.6, 1, CAST(N'2021-03-20' AS Date), CAST(N'2021-04-20' AS Date))
INSERT [dbo].[tblDiscounts] ([discountID], [discountName], [value], [status], [dateStart], [dateEnd]) VALUES (N'discount7', N'Discount 70%', 0.7, 1, CAST(N'2021-03-20' AS Date), CAST(N'2021-04-20' AS Date))
INSERT [dbo].[tblDiscounts] ([discountID], [discountName], [value], [status], [dateStart], [dateEnd]) VALUES (N'discount8', N'Discount 80%', 0.8, 1, CAST(N'2021-03-20' AS Date), CAST(N'2021-04-20' AS Date))
INSERT [dbo].[tblOrderDetails] ([carID], [orderID], [price], [quantity], [dateHire], [dateReturn], [numberDate], [status]) VALUES (N'car1', N'order2', 86, 2, CAST(N'2021-03-09' AS Date), CAST(N'2021-03-10' AS Date), 2, 1)
INSERT [dbo].[tblOrderDetails] ([carID], [orderID], [price], [quantity], [dateHire], [dateReturn], [numberDate], [status]) VALUES (N'car12', N'order2', 85, 1, CAST(N'2021-03-09' AS Date), CAST(N'2021-03-10' AS Date), 2, 1)
INSERT [dbo].[tblOrderDetails] ([carID], [orderID], [price], [quantity], [dateHire], [dateReturn], [numberDate], [status]) VALUES (N'car14', N'order1', 37, 2, CAST(N'2021-03-07' AS Date), CAST(N'2021-03-07' AS Date), 1, 1)
INSERT [dbo].[tblOrderDetails] ([carID], [orderID], [price], [quantity], [dateHire], [dateReturn], [numberDate], [status]) VALUES (N'car15', N'order1', 69, 1, CAST(N'2021-03-07' AS Date), CAST(N'2021-03-07' AS Date), 1, 1)
INSERT [dbo].[tblOrderDetails] ([carID], [orderID], [price], [quantity], [dateHire], [dateReturn], [numberDate], [status]) VALUES (N'car29', N'order4', 33, 1, CAST(N'2021-03-09' AS Date), CAST(N'2021-03-10' AS Date), 2, 0)
INSERT [dbo].[tblOrderDetails] ([carID], [orderID], [price], [quantity], [dateHire], [dateReturn], [numberDate], [status]) VALUES (N'car30', N'order4', 36, 1, CAST(N'2021-03-09' AS Date), CAST(N'2021-03-10' AS Date), 2, 0)
INSERT [dbo].[tblOrderDetails] ([carID], [orderID], [price], [quantity], [dateHire], [dateReturn], [numberDate], [status]) VALUES (N'car40', N'order3', 40, 1, CAST(N'2021-03-07' AS Date), CAST(N'2021-03-08' AS Date), 2, 1)
INSERT [dbo].[tblOrderDetails] ([carID], [orderID], [price], [quantity], [dateHire], [dateReturn], [numberDate], [status]) VALUES (N'car41', N'order3', 41, 1, CAST(N'2021-03-07' AS Date), CAST(N'2021-03-08' AS Date), 2, 1)
INSERT [dbo].[tblOrders] ([orderID], [total], [dateOrder], [userID], [discount], [status]) VALUES (N'order1', 143, CAST(N'2021-03-07' AS Date), N'leduytuanvu', N'', 1)
INSERT [dbo].[tblOrders] ([orderID], [total], [dateOrder], [userID], [discount], [status]) VALUES (N'order2', 411.20001220703125, CAST(N'2021-03-07' AS Date), N'leduytuanvu', N'Discount 20%', 1)
INSERT [dbo].[tblOrders] ([orderID], [total], [dateOrder], [userID], [discount], [status]) VALUES (N'order3', 162, CAST(N'2021-03-07' AS Date), N'leduytuanvu', N'', 1)
INSERT [dbo].[tblOrders] ([orderID], [total], [dateOrder], [userID], [discount], [status]) VALUES (N'order4', 66, CAST(N'2021-03-07' AS Date), N'leduytuanvu', N'', 0)
INSERT [dbo].[tblRoles] ([roleID], [roleName], [status]) VALUES (N'AD', N'Admin', 1)
INSERT [dbo].[tblRoles] ([roleID], [roleName], [status]) VALUES (N'US', N'User', 1)
INSERT [dbo].[tblTypeCars] ([typeID], [typeName], [status]) VALUES (N'type1', N'BMW', 1)
INSERT [dbo].[tblTypeCars] ([typeID], [typeName], [status]) VALUES (N'type2', N'Tesla', 1)
INSERT [dbo].[tblTypeCars] ([typeID], [typeName], [status]) VALUES (N'type3', N'Audi', 1)
INSERT [dbo].[tblTypeCars] ([typeID], [typeName], [status]) VALUES (N'type4', N'Cadillac', 1)
INSERT [dbo].[tblTypeCars] ([typeID], [typeName], [status]) VALUES (N'type5', N'Honda', 1)
INSERT [dbo].[tblTypeCars] ([typeID], [typeName], [status]) VALUES (N'type6', N'Hyundai', 1)
INSERT [dbo].[tblTypeCars] ([typeID], [typeName], [status]) VALUES (N'type7', N'Lexus', 1)
INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [phone], [email], [address], [roleID], [codeActive], [active], [status]) VALUES (N'admin', N'Admin', N'1', N'0961191732', N'admin@gmail.com', N'Bao Loc - Lam Dong', N'AD', N'03079', 1, 1)
INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [phone], [email], [address], [roleID], [codeActive], [active], [status]) VALUES (N'leduytuanvu', N'Le Duy Tuan Vu', N'1', N'0961191730', N'resort49k1@gmail.com', N'qwer/asdf/1234', N'US', N'46124', 1, 1)
INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [phone], [email], [address], [roleID], [codeActive], [active], [status]) VALUES (N'test', N'Test', N'1', N'0961191732', N'test@gmail.com', N'Bao Loc - Lam Dong', N'US', N'17852', 1, 1)
INSERT [dbo].[tblUsers] ([userID], [fullName], [password], [phone], [email], [address], [roleID], [codeActive], [active], [status]) VALUES (N'tuanvu', N'Le Duy Tuan Vu', N'1', N'0961191732', N'vuldtse141057@fpt.edu.vn', N'Bao Loc - Lam Dong', N'US', N'22789', 1, 1)
ALTER TABLE [dbo].[tblCars]  WITH CHECK ADD FOREIGN KEY([typeID])
REFERENCES [dbo].[tblTypeCars] ([typeID])
GO
ALTER TABLE [dbo].[tblOrderDetails]  WITH CHECK ADD FOREIGN KEY([carID])
REFERENCES [dbo].[tblCars] ([carID])
GO
ALTER TABLE [dbo].[tblOrderDetails]  WITH CHECK ADD FOREIGN KEY([orderID])
REFERENCES [dbo].[tblOrders] ([orderID])
GO
ALTER TABLE [dbo].[tblOrders]  WITH CHECK ADD FOREIGN KEY([userID])
REFERENCES [dbo].[tblUsers] ([userID])
GO
ALTER TABLE [dbo].[tblOrders]  WITH CHECK ADD FOREIGN KEY([userID])
REFERENCES [dbo].[tblUsers] ([userID])
GO
ALTER TABLE [dbo].[tblOrders]  WITH CHECK ADD FOREIGN KEY([userID])
REFERENCES [dbo].[tblUsers] ([userID])
GO
ALTER TABLE [dbo].[tblOrders]  WITH CHECK ADD FOREIGN KEY([userID])
REFERENCES [dbo].[tblUsers] ([userID])
GO
ALTER TABLE [dbo].[tblUsers]  WITH CHECK ADD FOREIGN KEY([roleID])
REFERENCES [dbo].[tblRoles] ([roleID])
GO
ALTER TABLE [dbo].[tblUsers]  WITH CHECK ADD FOREIGN KEY([roleID])
REFERENCES [dbo].[tblRoles] ([roleID])
GO
ALTER TABLE [dbo].[tblUsers]  WITH CHECK ADD FOREIGN KEY([roleID])
REFERENCES [dbo].[tblRoles] ([roleID])
GO
ALTER TABLE [dbo].[tblUsers]  WITH CHECK ADD FOREIGN KEY([roleID])
REFERENCES [dbo].[tblRoles] ([roleID])
GO
USE [master]
GO
ALTER DATABASE [Assignment3_LeDuyTuanVu] SET  READ_WRITE 
GO
